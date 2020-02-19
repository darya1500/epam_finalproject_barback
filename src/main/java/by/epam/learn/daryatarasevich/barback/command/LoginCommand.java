package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.User;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.logic.ClientType;
import by.epam.learn.daryatarasevich.barback.util.AppUtils;
import by.epam.learn.daryatarasevich.barback.logic.LoginLogic;
import by.epam.learn.daryatarasevich.barback.validation.LoginValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private LoginLogic loginLogic = new LoginLogic();
    LoginValidator loginValidator = new LoginValidator();
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    /**
     * To login user.
     * Email and password are validated. Then email and password are checked is they match information in database.
     * User role is being set.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean validated = loginValidator.validate(email, password);
        if (validated) {
            User user = loginLogic.check(email, password);
            ClientType clientType = loginLogic.checkRole(email, password);
            if (clientType != null) {
                request.getSession().setAttribute("role", clientType);
                request.getSession().setAttribute("USER", user);
                AppUtils.storeLogedUser(session, user);
                LOGGER.info(MessageManager.getProperty("message.successfullogin"));
                page = ConfigurationManager.getProperty("path.page.main");
            } else {
                request.getSession().setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                LOGGER.error(MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
        } else {
            request.getSession().setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerrorempty"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }

    /**
     * Boolean marks commands that require redirect instead of forward in ControllerServlet.
     *
     * @return true if page requires redirect
     */
    @Override
    public boolean requiresRedirect() {
        return true;
    }
}
