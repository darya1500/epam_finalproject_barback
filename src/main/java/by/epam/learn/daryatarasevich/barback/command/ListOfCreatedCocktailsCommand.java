package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.Cocktail;
import by.epam.learn.daryatarasevich.barback.logic.ListOfCocktailsLogic;
import by.epam.learn.daryatarasevich.barback.logic.RateCocktailLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ListOfCreatedCocktailsCommand implements ActionCommand {
    ListOfCocktailsLogic listOfCocktailsLogic = new ListOfCocktailsLogic();
    RateCocktailLogic rateCocktailLogic;
    private static final Logger LOGGER = LogManager.getLogger(ListOfCreatedCocktailsCommand.class);

    /**
     * Command defines actions executed to load list of created cocktails by the bartender from database.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Cocktail> cocktails = listOfCocktailsLogic.listSuggestedCocktails(request);
        request.setAttribute("COCKTAILS", cocktails);
        page = ConfigurationManager.getProperty("path.page.listofcreatedcocktails.list");
        rateCocktailLogic = new RateCocktailLogic();
        List<Cocktail> cocktails2 = listOfCocktailsLogic.listSuggestedCocktails(request);
        List<Integer> ratings = new ArrayList<>();
        if (cocktails2.size() > 0) {
            for (Cocktail cocktail : cocktails2) {
                int cocktailRating = (int) (rateCocktailLogic.getCocktailRate(cocktail) * 20);
                ratings.add(cocktailRating);
            }
            request.setAttribute("COCKTAILS_RATINGS", ratings);
        }
        return page;
    }
}