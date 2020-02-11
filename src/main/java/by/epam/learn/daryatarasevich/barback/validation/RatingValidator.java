package by.epam.learn.daryatarasevich.barback.validation;

import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RatingValidator {
    private static final Logger LOGGER = LogManager.getLogger(LoginValidation.class);

    public boolean validate(String star, String cocktailID, String authorID) {
        boolean validated=true;
        if (star==null||Integer.parseInt(star)==0){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.ratingisnull"));
        }
        if (cocktailID==null||Integer.parseInt(cocktailID)==0){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.cocktailidisnull"));
        }
        if (authorID==null||Integer.parseInt(authorID)==0){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.authoridisnull"));
        }
        return validated;
    }
}

