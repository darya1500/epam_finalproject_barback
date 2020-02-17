package by.epam.learn.daryatarasevich.barback.validation;

import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterValidator {
    private static final Logger LOGGER = LogManager.getLogger(RegisterValidator.class);

    public boolean validate(String email, String password,String nameEN) {
        boolean validated=true;

        if (email==null||email.equals("")){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.emailisnull"));
        }
        if (password==null||password.equals("")){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.passwordisnull"));
        }
        if (nameEN==null||nameEN.equals("")){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.nameisnull"));
        }
        return validated;
    }
    public boolean validateEmail(String email){
        boolean validated=false;
        char[] emailChars=email.toCharArray();
        if (emailChars.length<6){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.emailistooshort"));
        }
        boolean checked=false;
        for (Character ch:emailChars){
            if (ch.equals('@')){
                checked=true;
            }
        }
        boolean checked2=false;
        for (Character ch:emailChars){
            if (ch.equals('.')){
                checked2=true;
            }
        }
        if (checked&&checked2){
            validated=true;
            LOGGER.error(MessageManager.getProperty("message.emailisnotvalid"));
        }
        return validated;
    }

    public boolean validatePassword(String password){
        boolean validated=false;
        char[] passwordChars=password.toCharArray();
        if (passwordChars.length>=6){
            validated=true;
            LOGGER.error(MessageManager.getProperty("message.passwordistooshort"));
        }
        return validated;
    }
}

