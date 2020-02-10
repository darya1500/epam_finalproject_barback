package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.UserDAO;
import by.epam.learn.daryatarasevich.barback.entities.Status;
import by.epam.learn.daryatarasevich.barback.entities.User;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterLogic {
    private UserDAO userDAO = new UserDAO();
    private static final Logger LOGGER = LogManager.getLogger(RegisterLogic.class);
    /**
     * To register user. Beforehand to check if user is already registered.
     *
     * @param userNameEN
     * @param userNameRU
     * @param email
     * @param password
     * @param description
     * @param status
     * @throws NullPointerException
     * @return result
     */
    public boolean register(String userNameEN, String userNameRU, String email, String password, String description, Status status) {
        User theUser = null;
        boolean result = false;
        boolean isRegistered = userDAO.checkUser(email);
        User userX = new User(userNameEN, userNameRU, email, password, description, Status.USER);
        if (isRegistered) {
            result = false;
            LOGGER.info(MessageManager.getProperty("message.userisalreadyregistered"));
        } else {
            userDAO.registerUser(userX);
            theUser = userDAO.getT(userX);
            if (theUser == null) {
                    LOGGER.error(MessageManager.getProperty("message.errorwhileregistration"));
                    throw new NullPointerException(MessageManager.getProperty("message.errorwhileregistration"));
            }
            result = true;
        }
        return result;
    }
}