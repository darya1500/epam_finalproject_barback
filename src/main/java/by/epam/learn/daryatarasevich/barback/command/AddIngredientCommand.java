package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.Ingredient;
import by.epam.learn.daryatarasevich.barback.exception.IncorrectPasswordException;
import by.epam.learn.daryatarasevich.barback.exception.IngredientDBException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.exception.NoSuchUserException;
import by.epam.learn.daryatarasevich.barback.logic.ListOfIngredientsLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class AddIngredientCommand implements ActionCommand {
    ListOfIngredientsLogic listOfIngredientsLogic = new ListOfIngredientsLogic();
    private static final Logger LOGGER = LogManager.getLogger(AddIngredientCommand.class);

    /**
     * Command defines actions executed to add ingredient to the barbackdb.ingredients.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request){
        String page = null;
        try {
            listOfIngredientsLogic.addIngredient(request);
        } catch (NullPointerException e) {
            request.getSession().setAttribute("whileAddingIngredientMessage", MessageManager.getProperty("message.addingingredienterror"));
            LOGGER.error(MessageManager.getProperty("message.addingingredienterror"));
            page = ConfigurationManager.getProperty("path.page.addingredientform");
            return page;
        }
        page = ConfigurationManager.getProperty("path.page.main");
        request.getSession().setAttribute("whileAddingIngredientMessage", MessageManager.getProperty("message.successaddingingredient"));
        return page;
    }

    /**
     * Boolean marks commands that require redirect instead of forward in ControllerServlet.
     *
     * @return true if page requires redirect
     */
    @Override
    public boolean requiresRedirect() {
        return false;
    }
}
