package by.epam.learn.daryatarasevich.barback.validation;

import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginValidation {
    private static final Logger LOGGER = LogManager.getLogger(LoginValidation.class);

    public boolean validate(String email, String password) {
        boolean validated=true;
        if (email==null||email.equals("")){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.emailisnull"));
        }
        if (password==null||password.equals("")){
        validated=false;
            LOGGER.error(MessageManager.getProperty("message.passwordisnull"));
        }
        return validated;
    }
}
