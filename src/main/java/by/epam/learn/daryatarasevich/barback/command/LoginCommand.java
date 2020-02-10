package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entities.User;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.logic.ClientType;
import by.epam.learn.daryatarasevich.barback.utils.AppUtils;
import by.epam.learn.daryatarasevich.barback.logic.LoginLogic;
import by.epam.learn.daryatarasevich.barback.validation.LoginValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private LoginLogic loginLogic=new LoginLogic();
    LoginValidation loginValidation=new LoginValidation();
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
    public String execute(HttpServletRequest request)  {
        HttpSession session = request.getSession();
        String page = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean validated=loginValidation.validate(email,password);
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
                request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                LOGGER.error(MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
        }else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerrorempty"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
