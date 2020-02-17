package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class LocaleCommand implements ActionCommand {
    /**
     * Implementation of command to change language of web page.
     *
     * @param request .
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String localeValue = request.getParameter("locale");
        Locale locale;
        switch (localeValue) {
            case "ru_RU": {
                locale = new Locale("ru", "RU");
                break;
            }
            case "en_GB": {
                locale = new Locale("en", "GB");
                break;
            }
            default: {
                locale = new Locale("en", "GB");
                break;
            }
        }
        HttpSession session = request.getSession();
        String fmtLocale = Config.FMT_LOCALE;
        Config.set(session, fmtLocale, locale);
        page = getPage(request, session);
        return page;
    }

    /**
     * The method defines type of user and returns concrete page.
     *
     * @param request
     * @param session
     * @return page
     */
    private String getPage(HttpServletRequest request, HttpSession session) {
        String page = null;
        User user = (User) session.getAttribute("logeduser");
        if (user != null) {
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return page;
    }
}
