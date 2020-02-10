package by.epam.learn.daryatarasevich.barback.config;

import java.util.*;

public class SecurityConfig {
    public static final String ROLE_USER = "USER";
    public static final String ROLE_BARTENDER = "BARTENDER";
    public static final String ROLE_ADMINISTRATOR = "ADMINISTRATOR";

    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        //User
        List<String> urlPatterns1 = new ArrayList<String>();
        urlPatterns1.add("/views/login.jsp");
        urlPatterns1.add("/views/register.jsp");
        urlPatterns1.add("/views/help.jsp");
        urlPatterns1.add("/views/listofcocktails.jsp");
        urlPatterns1.add("/views/suggestcocktail.jsp");
        urlPatterns1.add("/views/assesscocktails.jsp");
        urlPatterns1.add("/views/listofsuggestedcocktails.jsp");
        urlPatterns1.add("/views/deleteaccount.jsp");
        mapConfig.put(ROLE_USER, urlPatterns1);
        //bartender
        List<String> urlPatterns2 = new ArrayList<String>();
        urlPatterns2.add("/views/login.jsp");
        urlPatterns2.add("/views/help.jsp");
        urlPatterns2.add("/views/listofcocktails.jsp");
        urlPatterns2.add("/views/bartender/createcocktail.jsp");
        urlPatterns2.add("/views/assesscocktails.jsp");
        urlPatterns2.add("/views/bartender/listofcreatedcocktails.jsp");
        urlPatterns2.add("/views/deleteaccount.jsp");
        mapConfig.put(ROLE_BARTENDER, urlPatterns2);
        //administrator
        List<String> urlPatterns3 = new ArrayList<String>();
        urlPatterns3.add("/login");
        urlPatterns3.add("/views/listofcocktails.jsp");
        urlPatterns3.add("/views/administrator/listofbartenders.jsp");
        urlPatterns3.add("/views/administrator/listofusers.jsp");
        urlPatterns3.add("/views/administrator/whoneedshelp.jsp");
        urlPatterns3.add("/views/administrator/cocktailstoapprove.jsp");
        urlPatterns3.add("/views/administrator/userstochangestatus.jsp");
        urlPatterns3.add("/views/administrator/addbartenderform.jsp");
        urlPatterns3.add("/views/administrator/addcocktailform.jsp");
        urlPatterns3.add("/views/administrator/addingredientform.jsp");
        urlPatterns3.add("/views/administrator/adduserform.jsp");
        urlPatterns3.add("/views/administrator/listofingredients.jsp");
        urlPatterns3.add("/views/administrator/updatebartenderform.jsp");
        urlPatterns3.add("/views/administrator/updatecocktailform.jsp");
        urlPatterns3.add("/views/administrator/updateingredientform.jsp");
        urlPatterns3.add("/views/administrator/updateuserform.jsp");
        urlPatterns3.add("/views/administrator/cocktailtoapprovepage.jsp");
        mapConfig.put(ROLE_ADMINISTRATOR, urlPatterns3);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
