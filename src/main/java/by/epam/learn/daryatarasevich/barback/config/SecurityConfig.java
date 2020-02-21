package by.epam.learn.daryatarasevich.barback.config;

import java.util.*;


public class SecurityConfig {
    public static final String ROLE_USER = "USER";
    public static final String ROLE_BARTENDER = "BARTENDER";
    public static final String ROLE_ADMINISTRATOR = "ADMINISTRATOR";
    private static final Map<String, List<String>> mapOfAccess = new HashMap<>();

    static {
        init();
    }
    /**
     * To define lists of jsp pages each user role has access to.
     * Roles are: USER, BARTENDER, ADMINISTRATOR.
     *
     */
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
        urlPatterns1.add("/login");
        urlPatterns1.add("/register");
        urlPatterns1.add("/help");
        urlPatterns1.add("/listofcocktails");
        urlPatterns1.add("/suggestcocktail");
        urlPatterns1.add("/assesscocktails");
        urlPatterns1.add("/listofsuggestedcocktails");
        urlPatterns1.add("/deleteaccount");
        mapOfAccess.put(ROLE_USER, urlPatterns1);
        //bartender
        List<String> urlPatterns2 = new ArrayList<String>();
        urlPatterns2.add("/login.jsp");
        urlPatterns2.add("/help.jsp");
        urlPatterns2.add("/listofcocktails.jsp");
        urlPatterns2.add("/bartender/createcocktail.jsp");
        urlPatterns2.add("/assesscocktails.jsp");
        urlPatterns2.add("/bartender/listofcreatedcocktails.jsp");
        urlPatterns2.add("/deleteaccount.jsp");
        urlPatterns2.add("/login");
        urlPatterns2.add("/help");
        urlPatterns2.add("/listofcocktails");
        urlPatterns2.add("/createcocktail");
        urlPatterns2.add("/assesscocktails");
        urlPatterns2.add("/listofcreatedcocktails");
        urlPatterns2.add("/deleteaccount");
        mapOfAccess.put(ROLE_BARTENDER, urlPatterns2);
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
        urlPatterns3.add("/login.jsp");
        urlPatterns3.add("/listofcocktails");
        urlPatterns3.add("/listofbartenders");
        urlPatterns3.add("/listofusers");
        urlPatterns3.add("/whoneedshelp");
        urlPatterns3.add("/cocktailstoapprove");
        urlPatterns3.add("/userstochangestatus");
        urlPatterns3.add("/addbartenderform");
        urlPatterns3.add("/addcocktailform");
        urlPatterns3.add("/addingredientform");
        urlPatterns3.add("/adduserform");
        urlPatterns3.add("/listofingredients");
        urlPatterns3.add("/updatebartenderform");
        urlPatterns3.add("/updatecocktailform");
        urlPatterns3.add("/updateingredientform");
        urlPatterns3.add("/updateuserform");
        urlPatterns3.add("/cocktailtoapprovepage");
        mapOfAccess.put(ROLE_ADMINISTRATOR, urlPatterns3);
    }
    /**
     * To get all keys(roles) from map.
     *
     */
    public static Set<String> getAllAppRoles() {
        return mapOfAccess.keySet();
    }
    /**
     * To get list of all pages this user role has access to.
     *
     */
    public static List<String> getUrlPatternsForRole(String role) {
        return mapOfAccess.get(role);
    }
}
