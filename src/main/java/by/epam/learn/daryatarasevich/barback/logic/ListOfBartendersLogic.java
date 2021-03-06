package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.UserDAO;
import by.epam.learn.daryatarasevich.barback.entity.Status;
import by.epam.learn.daryatarasevich.barback.entity.User;
import by.epam.learn.daryatarasevich.barback.exception.InvalidEmailException;
import by.epam.learn.daryatarasevich.barback.exception.InvalidPasswordException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.exception.RegistrationException;
import by.epam.learn.daryatarasevich.barback.validation.CommonValidator;
import by.epam.learn.daryatarasevich.barback.validation.RegisterValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListOfBartendersLogic {
    private UserDAO userDAO = new UserDAO();
    private static final Logger LOGGER = LogManager.getLogger(ListOfBartendersLogic.class);
    private final static String USER_NAME_EN = "userNameEN";
    private final static String USER_NAME_RU = "userNameRU";
    private final static String DESCRIPTION = "description";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";
    private final static String USER_ID = "userID";
    RegisterValidator registerValidator = new RegisterValidator();
    CommonValidator commonValidator = new CommonValidator();

    /**
     * To get all bartenders from database.
     *
     * @param request
     * @return users
     */
    public List<User> listBartenders(HttpServletRequest request) {
        List<User> users = userDAO.getBartenders();
        return users;
    }

    /**
     * To add bartender to database.
     *
     * @param request
     */
    public void addBartender(HttpServletRequest request) throws InvalidEmailException, InvalidPasswordException, RegistrationException {
        String userNameEN = request.getParameter(USER_NAME_EN);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        boolean validated = registerValidator.validate(email, password, userNameEN);
        boolean validatedEmail = registerValidator.validateEmail(email);
        boolean validatedPassword = registerValidator.validatePassword(password);
        if (validated && validatedEmail && validatedPassword) {
            boolean isRegistered = userDAO.checkUser(email);
            if (isRegistered) {
                LOGGER.info(MessageManager.getProperty("message.userisalreadyregistered"));
                throw new RegistrationException(MessageManager.getProperty("message.userisalreadyregistered"));
            }
            String userNameRU = request.getParameter(USER_NAME_RU);
            String description = request.getParameter(DESCRIPTION);
            User theUser = new User(userNameEN, userNameRU, email, password, description, Status.BARTENDER);
            userDAO.add(theUser);
        } else {
            throw new NullPointerException(MessageManager.getProperty("message.addbartendererror"));
        }
    }

    /**
     * To load bartender from database.
     *
     * @param request
     * @return theUser
     */
    public User loadBartender(HttpServletRequest request) {
        String theUserID = request.getParameter(USER_ID);
        User theUser = userDAO.getT(theUserID);
        return theUser;
    }

    /**
     * To update bartender in database.
     *
     * @param request
     */
    public void updateBartender(HttpServletRequest request) throws NullPointerException {
        int userID = Integer.parseInt(request.getParameter(USER_ID));
        String userNameEN = request.getParameter(USER_NAME_EN);
        boolean validated = commonValidator.validateUser(userNameEN);
        if (validated) {
            String userNameRU = request.getParameter(USER_NAME_RU);
            String email = request.getParameter(EMAIL);
            String password = request.getParameter(PASSWORD);
            String description = request.getParameter(DESCRIPTION);
            User theUser = new User(userID, userNameEN, userNameRU, email, password, description, Status.BARTENDER);
            userDAO.update(theUser);
        } else {
            throw new NullPointerException(MessageManager.getProperty("message.updatingbartendererror"));
        }
    }

    /**
     * To delete bartender from database.
     *
     * @param request
     */
    public void deleteBartender(HttpServletRequest request) {
        String theUserID = request.getParameter(USER_ID);
        userDAO.delete(theUserID);
    }
}
