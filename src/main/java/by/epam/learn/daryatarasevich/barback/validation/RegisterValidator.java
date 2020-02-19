package by.epam.learn.daryatarasevich.barback.validation;

import by.epam.learn.daryatarasevich.barback.exception.InvalidEmailException;
import by.epam.learn.daryatarasevich.barback.exception.InvalidPasswordException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterValidator {
    private static final Logger LOGGER = LogManager.getLogger(RegisterValidator.class);

    /**
     * To validate email,password, user name if they are not null, not empty lines.
     *
     * @param email
     * @param password
     * @param nameEN
     * @return true if validated
     */
    public boolean validate(String email, String password, String nameEN) {
        boolean validated = true;
        if (email == null || email.equals("")) {
            validated = false;
            LOGGER.error(MessageManager.getProperty("message.emailisnull"));
        }
        if (password == null || password.equals("")) {
            validated = false;
            LOGGER.error(MessageManager.getProperty("message.passwordisnull"));
        }
        if (nameEN == null || nameEN.equals("")) {
            validated = false;
            LOGGER.error(MessageManager.getProperty("message.nameisnull"));
        }
        return validated;
    }

    /**
     * To validate email if it is 6 symbols or longer and includes "@" and "."
     *
     * @param email
     * @return true if validated
     * @throws InvalidEmailException
     */
    public boolean validateEmail(String email) throws InvalidEmailException {
        boolean validated = false;
        char[] emailChars = email.toCharArray();
        if (emailChars.length < 6) {
            validated = false;
            LOGGER.error(MessageManager.getProperty("message.emailistooshort"));
        }
        boolean checked = false;
        for (Character ch : emailChars) {
            if (ch.equals('@')) {
                checked = true;
            }
        }
        boolean checked2 = false;
        for (Character ch : emailChars) {
            if (ch.equals('.')) {
                checked2 = true;
            }
        }
        if (checked && checked2) {
            validated = true;
        } else {
            LOGGER.error(MessageManager.getProperty("message.emailisnotvalid"));
            throw new InvalidEmailException(MessageManager.getProperty("message.emailisnotvalid"));
        }
        return validated;
    }

    /**
     * To validate password if it is 6 symbols or longer.
     *
     * @param password
     * @return true if validated
     * @throws InvalidPasswordException
     */
    public boolean validatePassword(String password) throws InvalidPasswordException {
        boolean validated = false;
        char[] passwordChars = password.toCharArray();
        if (passwordChars.length >= 6) {
            validated = true;
        } else {
            LOGGER.error(MessageManager.getProperty("message.passwordistooshort"));
            throw new InvalidPasswordException(MessageManager.getProperty("message.passwordistooshort"));
        }
        return validated;
    }
}

