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
        urlPatterns1.add("/login.jsp");
        urlPatterns1.add("/register.jsp");
        urlPatterns1.add("/help.jsp");
        urlPatterns1.add("/listofcocktails.jsp");
        urlPatterns1.add("/suggestcocktail.jsp");
        urlPatterns1.add("/assesscocktails.jsp");
        urlPatterns1.add("/listofsuggestedcocktails.jsp");
        urlPatterns1.add("/deleteaccount.jsp");
        mapConfig.put(ROLE_USER, urlPatterns1);
        //bartender
        List<String> urlPatterns2 = new ArrayList<String>();
        urlPatterns2.add("/login.jsp");
        urlPatterns2.add("/help.jsp");
        urlPatterns2.add("/listofcocktails.jsp");
        urlPatterns2.add("/bartender/createcocktail.jsp");
        urlPatterns2.add("/assesscocktails.jsp");
        urlPatterns2.add("/bartender/listofcreatedcocktails.jsp");
        urlPatterns2.add("/deleteaccount.jsp");
        mapConfig.put(ROLE_BARTENDER, urlPatterns2);
        //administrator
        List<String> urlPatterns3 = new ArrayList<String>();
        urlPatterns3.add("/login");
        urlPatterns3.add("/listofcocktails.jsp");
        urlPatterns3.add("/administrator/listofbartenders.jsp");
        urlPatterns3.add("/administrator/listofusers.jsp");
        urlPatterns3.add("/administrator/whoneedshelp.jsp");
        urlPatterns3.add("/administrator/cocktailstoapprove.jsp");
        urlPatterns3.add("/administrator/userstochangestatus.jsp");
        urlPatterns3.add("/administrator/addbartenderform.jsp");
        urlPatterns3.add("/administrator/addcocktailform.jsp");
        urlPatterns3.add("/administrator/addingredientform.jsp");
        urlPatterns3.add("/administrator/adduserform.jsp");
        urlPatterns3.add("/administrator/listofingredients.jsp");
        urlPatterns3.add("/administrator/updatebartenderform.jsp");
        urlPatterns3.add("/administrator/updatecocktailform.jsp");
        urlPatterns3.add("/administrator/updateingredientform.jsp");
        urlPatterns3.add("/administrator/updateuserform.jsp");
        urlPatterns3.add("/administrator/cocktailtoapprovepage.jsp");
        mapConfig.put(ROLE_ADMINISTRATOR, urlPatterns3);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
