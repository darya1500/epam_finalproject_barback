package by.epam.learn.daryatarasevich.barback.validation;

import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonValidator {
    private static final Logger LOGGER = LogManager.getLogger(CommonValidator.class);

    /**
     * To validate cocktail ID if it is number, not null, not 0 or less.
     *
     * @param theCocktailID
     * @return true if validated
     */
    public boolean validateID(String theCocktailID) {
        boolean validated = true;
        int id = 0;
        try {
            id = Integer.parseInt(theCocktailID);
        } catch (NumberFormatException e) {
            LOGGER.error(MessageManager.getProperty("message.idisincorrect"));
            return false;
        }
        if (theCocktailID == null || id <= 0) {
            validated = false;
            LOGGER.error(MessageManager.getProperty("message.idisincorrect"));
        }
        return validated;
    }

    /**
     * To validate cocktail by cocktail name if it is not null and not empty line.
     *
     * @param name
     * @return true if validated
     */
    public boolean validateCocktail(String name) {
        boolean validated = true;
        if (name == null || name.equals("")) {
            validated = false;
            LOGGER.error(MessageManager.getProperty("message.nameisnull"));
        }
        return validated;
    }

    /**
     * To validate user by user name if it is not null, not empty line.
     *
     * @param name
     * @return true if validated
     */
    public boolean validateUser(String name) {
        boolean validated = true;
        if (name == null || name.equals("")) {
            validated = false;
            LOGGER.error(MessageManager.getProperty("message.nameisnull"));
        }
        return validated;
    }

    /**
     * To validate ingredient by ingredient name if it is not null, not empty line.
     *
     * @param name
     * @return true if validated
     */
    public boolean validateIngredient(String name) {
        boolean validated = true;
        if (name == null || name.equals("")) {
            validated = false;
            LOGGER.error(MessageManager.getProperty("message.nameisnull"));
        }
        return validated;
    }
}
