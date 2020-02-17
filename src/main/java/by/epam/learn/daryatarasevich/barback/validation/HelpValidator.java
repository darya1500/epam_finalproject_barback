package by.epam.learn.daryatarasevich.barback.validation;

import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelpValidator {
    private static final Logger LOGGER = LogManager.getLogger(HelpValidator.class);

    public boolean validate(String email, String name,String message) {
        boolean validated=true;
        if (email==null||email.equals("")){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.emailisnull"));
        }
        if (name==null||name.equals("")){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.nameisnull"));
        }
        if (message==null||message.equals("")){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.messageisnull"));
        }
        return validated;
    }
}

