package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entities.Cocktail;
import by.epam.learn.daryatarasevich.barback.entities.Ingredient;
import by.epam.learn.daryatarasevich.barback.logic.ListOfCocktailsLogic;
import by.epam.learn.daryatarasevich.barback.logic.ListOfCocktailsToApproveLogic;
import by.epam.learn.daryatarasevich.barback.logic.ListOfIngredientsLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListOfCocktailsToApproveCommand implements ActionCommand {
    ListOfCocktailsToApproveLogic listOfCocktailsToApproveLogic;
    ListOfIngredientsLogic listOfIngredientsLogic;
    ListOfCocktailsLogic listOfCocktailsLogic;
    private static final Logger LOGGER = LogManager.getLogger(ListOfCocktailsToApproveCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws NamingException {
        String page = null;
        List<Cocktail> cocktails = null;
        listOfCocktailsToApproveLogic = new ListOfCocktailsToApproveLogic();
        String theOperation = request.getParameter("operation");
        if (theOperation == null) {
            theOperation = "LIST";
        }
        switch (theOperation) {
            case "LIST":
                cocktails = listOfCocktailsToApproveLogic.listCocktails(request);
                request.setAttribute("COCKTAILS", cocktails);
                page = ConfigurationManager.getProperty("path.page.listofcocktailstoapprove");
                break;
            case "APPROVE":
                listOfCocktailsToApproveLogic.approveCocktail (request);
                listOfCocktailsLogic=new ListOfCocktailsLogic();
                Cocktail cocktail=listOfCocktailsToApproveLogic.loadCocktail(request);
                listOfCocktailsLogic.addCocktailApproved(cocktail);
                request.setAttribute("SUCCESS_MESSAGE","Cocktail is successfully approved");
                cocktails = listOfCocktailsToApproveLogic.listCocktails(request);
                request.setAttribute("COCKTAILS", cocktails);
                page = ConfigurationManager.getProperty("path.page.listofcocktailstoapprove");
                break;
            case "LOAD":
                Cocktail cocktail2 = listOfCocktailsToApproveLogic.loadCocktail(request);
                request.setAttribute("THE_COCKTAIL", cocktail2);
                listOfIngredientsLogic = new ListOfIngredientsLogic();
                List<Ingredient> ingredients = listOfIngredientsLogic.getAllByCocktail(cocktail2);
                request.setAttribute("INGREDIENTS", ingredients);
                page = ConfigurationManager.getProperty("path.page.cocktailtoapprovepage");
                break;
            case "DELETE":
                listOfCocktailsToApproveLogic.deleteCocktail(request);
                cocktails = listOfCocktailsToApproveLogic.listCocktails(request);
                request.setAttribute("COCKTAILS", cocktails);
                page = ConfigurationManager.getProperty("path.page.listofcocktailstoapprove");
                break;
        }
        return page;
    }
}