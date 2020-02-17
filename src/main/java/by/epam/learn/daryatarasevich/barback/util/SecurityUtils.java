package by.epam.learn.daryatarasevich.barback.util;

import by.epam.learn.daryatarasevich.barback.config.SecurityConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


public class SecurityUtils {
    // check if this 'request' needs logging.
    public static boolean isSecurityPage(HttpServletRequest request) {
//        String urlPattern = UrlPatternUtils.getUrlPattern (request);
        String servletPath = request.getServletPath();
        Set<String> roles = SecurityConfig.getAllAppRoles();
        for (String role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(servletPath)) {
                return true;
            }
        }
        return false;
    }

    // check if this 'request'has suitable role
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        Set<String> allRoles = SecurityConfig.getAllAppRoles();
        for (String role : allRoles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}
