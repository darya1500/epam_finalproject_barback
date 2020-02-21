package by.epam.learn.daryatarasevich.barback.validation;

import by.epam.learn.daryatarasevich.barback.exception.InvalidEmailException;
import by.epam.learn.daryatarasevich.barback.exception.InvalidPasswordException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (email.toCharArray().length < 6) {
            validated = false;
            LOGGER.error(MessageManager.getProperty("message.emailistooshort"));
        }
        boolean checked = false;
        Pattern p=Pattern.compile("@");
        Matcher m=p.matcher(email);
            if (m.find()) {
                checked = true;
            }
        boolean checked2 = false;
        Pattern p2=Pattern.compile("\\.");
        Matcher m2=p2.matcher(email);
            if (m2.find()) {
                checked2 = true;
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

