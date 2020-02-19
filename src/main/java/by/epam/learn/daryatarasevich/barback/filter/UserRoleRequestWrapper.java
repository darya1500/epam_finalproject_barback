package by.epam.learn.daryatarasevich.barback.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UserRoleRequestWrapper extends HttpServletRequestWrapper {
    private String user;
    private String roles = null;
    private HttpServletRequest realRequest;

    public UserRoleRequestWrapper(String user, String role, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.roles = role;
        this.realRequest = request;
    }

    /**
     * To check if user is in role.
     *
     * @param role
     * @return true or false
     */
    @Override
    public boolean isUserInRole(String role) {
        if (roles == null) {
            return this.realRequest.isUserInRole(role);
        }
        return roles.contains(role);
    }
}
