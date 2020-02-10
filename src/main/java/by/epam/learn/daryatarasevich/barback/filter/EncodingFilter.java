package by.epam.learn.daryatarasevich.barback.filter;

import by.epam.learn.daryatarasevich.barback.exception.ConnectionPoolException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, initParams = {@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")})
public class EncodingFilter implements Filter {
    private String code;
    private final static String ENCODING="encoding";
    /**
     * To init filter.
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig){
        code = filterConfig.getInitParameter(ENCODING);
    }
    /**
     * To perform filter work.
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException,ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }
    /**
     * To destroy filter.
     *
     */
    @Override
    public void destroy() {
        code = null;
    }
}
