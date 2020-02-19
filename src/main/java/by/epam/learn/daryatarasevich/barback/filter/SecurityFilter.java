package by.epam.learn.daryatarasevich.barback.filter;

import by.epam.learn.daryatarasevich.barback.entity.User;
import by.epam.learn.daryatarasevich.barback.util.AppUtils;
import by.epam.learn.daryatarasevich.barback.util.SecurityUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {
    private final static String LOGIN = "/login";
    private final static String INDEX = "/index";
    private final static String LOGIN_REDIRECT = "/login?redirectId=";

    public SecurityFilter() {
    }

    /**
     * To init filter.
     *
     * @param fConfig
     */
    @Override
    public void init(FilterConfig fConfig) {
    }

    /**
     * To perform security filtering.
     * First to get user information that is saved in Session after successful logging in.
     * If servlet path equals /login then perform filtering.
     * Else check if page needs user to be loged in and does the user has permission to go this page.
     *
     * @param request1
     * @param response1
     * @param chain
     * @throws IOException,ServletException
     */
    @Override
    public void doFilter(ServletRequest request1, ServletResponse response1, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) request1;
        HttpServletResponse response = (HttpServletResponse) response1;
        String servletPath = request.getServletPath();
        User logedUser = AppUtils.getLogedUser(request.getSession());
        if (servletPath.equals(LOGIN)) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest request2 = request;
        if (logedUser != null) {
            String userName = logedUser.getName();
            String role = logedUser.getStatus().toString();
            request2 = new UserRoleRequestWrapper(userName, role, request);
        }
        if (SecurityUtils.isSecurityPage(request)) {
            if (logedUser == null) {
                String requestUri = request.getRequestURI();
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);
                response.sendRedirect(request2.getContextPath() + LOGIN_REDIRECT + redirectId);
                return;
            }
            boolean hasPermission = SecurityUtils.hasPermission(request2);
            if (!hasPermission) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(INDEX);
                dispatcher.forward(request, response);
                return;
            }
        }
        chain.doFilter(request2, response);
    }

    /**
     * To destroy filter.
     */
    @Override
    public void destroy() {
    }
}
