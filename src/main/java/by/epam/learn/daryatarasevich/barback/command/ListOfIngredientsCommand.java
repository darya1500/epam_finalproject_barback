package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entities.Ingredient;
import by.epam.learn.daryatarasevich.barback.entities.User;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.logic.ListOfIngredientsLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListOfIngredientsCommand implements ActionCommand {
    private ListOfIngredientsLogic listOfIngredientsLogic = new ListOfIngredientsLogic();
    private static final Logger LOGGER = LogManager.getLogger(ListOfIngredientsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Ingredient> ingredients = null;
        String theOperation = request.getParameter("operation");
        if (theOperation == null) {
            theOperation = "LIST";
        }
        switch (theOperation) {
            case "LIST":
                ingredients = listOfIngredientsLogic.listIngredients(request);
                request.setAttribute("INGREDIENTS", ingredients);
                page = ConfigurationManager.getProperty("path.page.listofuingredients.list");
                break;
            case "ADD":
                try {
                    listOfIngredientsLogic.addIngredient(request);
                } catch (NullPointerException e) {
                    request.setAttribute("message", MessageManager.getProperty("message.addingingredienterror"));
                    LOGGER.error(MessageManager.getProperty("message.addingingredienterror"));
                    ingredients = listOfIngredientsLogic.listIngredients(request);
                    request.setAttribute("INGREDIENTS", ingredients);
                    page = ConfigurationManager.getProperty("path.page.listofuingredients.list");
                    return page;
                }
                ingredients = listOfIngredientsLogic.listIngredients(request);
                request.setAttribute("INGREDIENTS", ingredients);
                page = ConfigurationManager.getProperty("path.page.listofuingredients.list");
                break;
            case "LOAD":
                Ingredient theIngredient = listOfIngredientsLogic.loadIngredient(request);
                request.setAttribute("THE_INGREDIENT", theIngredient);
                page = ConfigurationManager.getProperty("path.page.listofuingredients.updateform");
                break;
            case "UPDATE":
                try {
                    listOfIngredientsLogic.updateIngredient(request);
                } catch (NullPointerException e) {
                    request.setAttribute("message", MessageManager.getProperty("message.updatingingingredienterror"));
                    LOGGER.error(MessageManager.getProperty("message.updatingingingredienterror"));
                    ingredients = listOfIngredientsLogic.listIngredients(request);
                    request.setAttribute("INGREDIENTS", ingredients);
                    page = ConfigurationManager.getProperty("path.page.listofuingredients.list");
                    return page;
                }
                ingredients = listOfIngredientsLogic.listIngredients(request);
                request.setAttribute("INGREDIENTS", ingredients);
                page = ConfigurationManager.getProperty("path.page.listofuingredients.list");
                break;
            case "DELETE":
                listOfIngredientsLogic.deleteIngredient(request);
                ingredients = listOfIngredientsLogic.listIngredients(request);
                request.setAttribute("INGREDIENTS", ingredients);
                page = ConfigurationManager.getProperty("path.page.listofuingredients.list");
                break;
            default:
                ingredients = listOfIngredientsLogic.listIngredients(request);
                request.setAttribute("INGREDIENTS", ingredients);
                page = ConfigurationManager.getProperty("path.page.listofuingredients.list");
        }
        return page;
    }
}
