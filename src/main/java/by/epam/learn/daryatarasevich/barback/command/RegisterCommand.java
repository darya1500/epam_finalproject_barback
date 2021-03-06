package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.Status;
import by.epam.learn.daryatarasevich.barback.entity.User;
import by.epam.learn.daryatarasevich.barback.exception.InvalidEmailException;
import by.epam.learn.daryatarasevich.barback.exception.InvalidPasswordException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.logic.ClientType;
import by.epam.learn.daryatarasevich.barback.logic.ListOfUsersLogic;
import by.epam.learn.daryatarasevich.barback.logic.RegisterLogic;
import by.epam.learn.daryatarasevich.barback.util.AppUtils;
import by.epam.learn.daryatarasevich.barback.validation.RegisterValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements ActionCommand {
    private RegisterLogic registerLogic = new RegisterLogic();
    ListOfUsersLogic listOfUsersLogic = new ListOfUsersLogic();
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
    RegisterValidator registerValidator = new RegisterValidator();

    /**
     * To register user.
     * At first to validate data input from user.If it is correct to check if such user is already registered.
     * If such user is not registered then to register this user.
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
        String userNameEN = request.getParameter("userNameEN");
        boolean validated = registerValidator.validate(email, password, userNameEN);
        if (validated) {
            boolean validatedEmail = false;
            try {
                validatedEmail = registerValidator.validateEmail(email);
            } catch (InvalidEmailException e) {
                request.setAttribute("errorMessage", MessageManager.getProperty("message.emailisnotvalid"));
                LOGGER.error(MessageManager.getProperty("message.emailisnotvalid"));
                page = ConfigurationManager.getProperty("path.page.register");
                return page;
            }
            if (validatedEmail) {
                boolean validatedPassword = false;
                try {
                    validatedPassword = registerValidator.validatePassword(password);
                } catch (InvalidPasswordException e) {
                    request.setAttribute("errorMessage", MessageManager.getProperty("message.passwordistooshort"));
                    LOGGER.error(MessageManager.getProperty("message.passwordistooshort"));
                    page = ConfigurationManager.getProperty("path.page.register");
                    return page;
                }
                if (validatedPassword) {
                    String userNameRU = request.getParameter("userNameRU");
                    String description = request.getParameter("description");
                    User theUser = new User(userNameEN, userNameRU, email, password, description, Status.USER);
                    boolean ableToRegister = registerLogic.register(userNameEN, userNameRU, email, password, description, Status.USER);
                    if (!ableToRegister) {
                        request.setAttribute("errorMessage", MessageManager.getProperty("message.alreadyregistered"));
                        LOGGER.error(MessageManager.getProperty("message.alreadyregistered"));
                        page = ConfigurationManager.getProperty("path.page.register");
                    } else {
                        theUser = listOfUsersLogic.loadUserByEmail(email);
                        request.getSession().setAttribute("role", ClientType.USER);
                        AppUtils.storeLogedUser(session, theUser);
                        request.setAttribute("USER", theUser);
                        request.setAttribute("message", MessageManager.getProperty("message.successfulregister"));
                        LOGGER.info(MessageManager.getProperty("message.successfulregister"));
                        page = ConfigurationManager.getProperty("path.page.main");
                    }
                }
            }
        } else {
            request.setAttribute("errorMessage", MessageManager.getProperty("message.registererrorempty"));
            LOGGER.error(MessageManager.getProperty("message.registererrorempty"));
            page = ConfigurationManager.getProperty("path.page.register");
            return page;
        }
        return page;
    }
}
