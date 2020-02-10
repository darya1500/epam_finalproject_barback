package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entities.Cocktail;
import by.epam.learn.daryatarasevich.barback.entities.Ingredient;
import by.epam.learn.daryatarasevich.barback.entities.User;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.exception.RatingErrorException;
import by.epam.learn.daryatarasevich.barback.logic.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class RateCocktailCommand implements ActionCommand {
    RateCocktailLogic rateCocktailLogic=new RateCocktailLogic();
    ListOfCocktailsLogic listOfCocktailsLogic=new ListOfCocktailsLogic();
    ListOfUsersLogic listOfUsersLogic=new ListOfUsersLogic();
    ListOfIngredientsLogic listOfIngredientsLogic=new ListOfIngredientsLogic();
    private static final Logger LOGGER = LogManager.getLogger(RateCocktailCommand.class);
    /**
     * To rate cocktail.
     * To get cocktail,author and ingredients data from database.
     *To check if user has already rated cocktail. If not then to rate cocktail.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Cocktail cocktail=listOfCocktailsLogic.loadCocktail(request);
        if (cocktail==null){
            request.setAttribute("message", MessageManager.getProperty("message.ratingerror"));
            LOGGER.error(MessageManager.getProperty("message.ratingerror"));
            page = ConfigurationManager.getProperty("path.page.cocktailpage");
            return page;
        }
        request.setAttribute("COCKTAIL",cocktail);
        int authorID=cocktail.getAuthor().getId();
        User author=listOfUsersLogic.loadUserByID(authorID);
        request.setAttribute ("AUTHOR", author);
        ArrayList<Ingredient> ingredients=listOfIngredientsLogic.getAllByCocktail(cocktail);
        request.setAttribute("INGREDIENTS",ingredients);
        boolean rated=rateCocktailLogic.checkIfRated(request);
        if (rated){
            request.setAttribute("message", MessageManager.getProperty("message.ratingerror"));
            LOGGER.error(MessageManager.getProperty("message.ratingerror"));
            page = ConfigurationManager.getProperty("path.page.cocktailpage");
            return page;
        }else{
            try {
                rateCocktailLogic.rate(request);
            } catch (RatingErrorException e) {
                e.printStackTrace();
                request.setAttribute("message", MessageManager.getProperty("message.errorrating"));
                LOGGER.error(MessageManager.getProperty("message.errorrating"));
                page = ConfigurationManager.getProperty("path.page.cocktailpage");
                return page;
            }
            request.setAttribute("message", MessageManager.getProperty("message.successratingmessage"));
            LOGGER.info(MessageManager.getProperty("message.successratingmessage"));
            page = ConfigurationManager.getProperty("path.page.cocktailpage");
        }
        return page;
    }
}