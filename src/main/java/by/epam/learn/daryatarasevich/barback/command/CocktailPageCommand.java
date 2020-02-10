package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entities.Cocktail;
import by.epam.learn.daryatarasevich.barback.entities.Ingredient;
import by.epam.learn.daryatarasevich.barback.entities.User;
import by.epam.learn.daryatarasevich.barback.exception.MessageConstants;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.logic.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CocktailPageCommand implements ActionCommand {
    ListOfCocktailsLogic listOfCocktailsLogic=new ListOfCocktailsLogic();
    ListOfUsersLogic listOfUsersLogic=new ListOfUsersLogic();
    ListOfIngredientsLogic listOfIngredientsLogic=new ListOfIngredientsLogic();
    RateCocktailLogic rateCocktailLogic=new RateCocktailLogic();
    private static final Logger LOGGER = LogManager.getLogger(CocktailPageCommand.class);
    private final static String AUTHOR = "AUTHOR";
    private final static String COCKTAIL = "COCKTAIL";
    private final static String INGREDIENTS ="INGREDIENTS";
    private final static String AUTHOR_RATING = "AUTHOR_RATING";
    private final static String COCKTAIL_RATING ="COCKTAIL_RATING";
    /**
     * Command defines actions executed before going to cocktailpage.jsp
     *
     * @param request
     * @throws NullPointerException
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page=null;
        Cocktail cocktail=listOfCocktailsLogic.loadCocktail(request);
        if (cocktail==null){
            LOGGER.error(MessageManager.getProperty("message.cocktailisnull"));
            throw new NullPointerException( MessageManager.getProperty("message.cocktailisnull"));
        }
        int authorID=cocktail.getAuthor().getId();
        User author=listOfUsersLogic.loadUserByID(authorID);
        ArrayList<Ingredient> ingredients=listOfIngredientsLogic.getAllByCocktail(cocktail);
        request.setAttribute (AUTHOR, author);
        request.setAttribute(COCKTAIL,cocktail);
        request.setAttribute(INGREDIENTS,ingredients);
        double authorRating=rateCocktailLogic.getAuthorRating(authorID);
        int authorRate= (int) (authorRating*20);
        double cocktailRating=rateCocktailLogic.getCocktailRate(cocktail);
        int cocktailRate= (int) (cocktailRating*20);
        request.setAttribute(AUTHOR_RATING,authorRate);
        request.setAttribute(COCKTAIL_RATING,cocktailRate);
        page = ConfigurationManager.getProperty("path.page.cocktailpage");
        return page;
    }
}
