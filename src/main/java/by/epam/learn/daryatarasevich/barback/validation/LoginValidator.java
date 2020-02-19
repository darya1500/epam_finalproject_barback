package by.epam.learn.daryatarasevich.barback.validation;

import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginValidator {
    private static final Logger LOGGER = LogManager.getLogger(LoginValidator.class);

    /**
     * To validate email and password if it is not null, not empty line.
     *
     * @param email
     * @param password
     * @return true if validated
     */
    public boolean validate(String email, String password) {
        boolean validated = true;
        if (email == null || email.equals("")) {
            validated = false;
            LOGGER.error(MessageManager.getProperty("message.emailisnull"));
            return validated;
        }
        if (password == null || password.equals("")) {
            validated = false;
            LOGGER.error(MessageManager.getProperty("message.passwordisnull"));
            return validated;
        }
        return validated;
    }
}
