package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.Cocktail;
import by.epam.learn.daryatarasevich.barback.exception.IngredientDBException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.logic.CreateCocktailLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateCocktailCommand implements ActionCommand {
    CreateCocktailLogic createCocktailLogic = new CreateCocktailLogic();
    private static final Logger LOGGER = LogManager.getLogger(CreateCocktailCommand.class);

    /**
     * Command defines actions executed to create cocktail.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Cocktail cocktail = null;
        try {
            cocktail = createCocktailLogic.getCocktail(request);
        } catch (NullPointerException e) {
            request.getSession().setAttribute("whileAddingCocktailMessage", MessageManager.getProperty("message.createcocktailerror"));
            page = ConfigurationManager.getProperty("path.page.main");
            LOGGER.error(MessageManager.getProperty("message.cocktailisnull"));
            return page;
        }
        try {
            createCocktailLogic.addCocktail(cocktail);
        } catch (NullPointerException e) {
            request.getSession().setAttribute("whileAddingCocktailMessage", MessageManager.getProperty("message.createcocktailerror"));
            page = ConfigurationManager.getProperty("path.page.main");
            LOGGER.error(MessageManager.getProperty("message.cocktailisnull"));
            return page;
        }
        request.getSession().setAttribute("whileAddingCocktailMessage", MessageManager.getProperty("message.successcreatecocktailmessage"));
        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }

    @Override
    public boolean requiresRedirect() {
        return true;
    }
}


