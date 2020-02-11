package by.epam.learn.daryatarasevich.barback.validation;

import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Validation {
    private static final Logger LOGGER = LogManager.getLogger(Validation.class);


    public boolean validateID(String theCocktailID) {
        boolean validated=true;
        int id=0;
        try{
            id=Integer.parseInt(theCocktailID);
        }catch (NumberFormatException e){
            LOGGER.error(MessageManager.getProperty("message.idisincorrect"));
            return false;
        }
        if (theCocktailID==null||id==0){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.idisincorrect"));
        }
        return validated;
    }

    public boolean validateCocktail(String name){
        boolean validated=true;
        if (name==null||name.equals("")){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.nameisnull"));
        }
        return validated;
    }

    public boolean validateUser(String name) {
        boolean validated=true;
        if (name==null||name.equals("")){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.nameisnull"));
        }
        return validated;
    }
    public boolean validateIngredient(String name) {
        boolean validated=true;
        if (name==null||name.equals("")){
            validated=false;
            LOGGER.error(MessageManager.getProperty("message.nameisnull"));
        }
        return validated;
    }
}
