package by.epam.learn.daryatarasevich.barback.util;

import by.epam.learn.daryatarasevich.barback.entity.User;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AppUtils {
    private static int REDIRECT_ID = 0;
    private static final Map<Integer, String> id_uri_map = new HashMap<>();
    private static final Map<String, Integer> uri_id_map = new HashMap<>();
    private final static String LOGED_USER="logedUser";
    /**
     * To save user information into Session.
     *
     * @param session
     * @param logedUser
     */
    public static void storeLogedUser(HttpSession session, User logedUser) {
        session.setAttribute(LOGED_USER, logedUser);
    }
    /**
     * To get user information from Session.
     *
     * @param session
     *@return logedUser
     */
    public static User getLogedUser(HttpSession session) {
        User logedUser = (User) session.getAttribute(LOGED_USER);
        return logedUser;
    }
    /**
     * To store redirect after login.
     *
     * @param session
     * @param requestUri
     * @return id
     */
    public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
        Integer id = uri_id_map.get(requestUri);
        if (id == null) {
            id = REDIRECT_ID++;
            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
            return id;
        }
        return id;
    }
    /**
     * To get redirect after login.
     *
     * @param session
     * @param redirectId
     * @return null
     */
    public static String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
        String url = id_uri_map.get(redirectId);
        if (url != null) {
            return url;
        }
        return null;
    }

}