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
    private final static String LOGIN="/login";
    private final static String INDEX="/index";
    private final static String LOGIN_REDIRECT="/login?redirectId=";

    public SecurityFilter() {
    }
    /**
     * To perform work of filter in chain of filters.
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
        // User information is saved in Session after successful logging in
        User logedUser = AppUtils.getLogedUser(request.getSession());
        if (servletPath.equals(LOGIN)) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;
        if (logedUser != null) {
            String userName = logedUser.getName();
            String role = logedUser.getStatus().toString();
            wrapRequest = new UserRoleRequestWrapper(userName, role, request);
        }
        // Page that needs login
        if (SecurityUtils.isSecurityPage(request)) {
            if (logedUser == null) {
                String requestUri = request.getRequestURI();
                // Save current page for redirect after successful logging in
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);
                response.sendRedirect(wrapRequest.getContextPath() + LOGIN_REDIRECT + redirectId);
                return;
            }
            // Check if user has a role
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(INDEX);
                dispatcher.forward(request, response);
                return;
            }
        }
        chain.doFilter(wrapRequest, response);
    }
    /**
     * To init filter.
     *
     * @param fConfig
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }
    /**
     * To destroy filter.
     *
     */
    @Override
    public void destroy() {
    }
}
