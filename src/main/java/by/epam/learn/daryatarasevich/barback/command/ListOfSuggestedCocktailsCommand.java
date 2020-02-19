package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.Cocktail;
import by.epam.learn.daryatarasevich.barback.logic.ListOfCocktailsLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListOfSuggestedCocktailsCommand implements ActionCommand {
    private ListOfCocktailsLogic listOfCocktailsLogic = new ListOfCocktailsLogic();
    private static final Logger LOGGER = LogManager.getLogger(ListOfSuggestedCocktailsCommand.class);

    /**
     * Command defines actions executed to load list of suggested by user and approved by administrator cocktails  from database.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Cocktail> cocktails = listOfCocktailsLogic.listSuggestedCocktails(request);
        request.setAttribute("COCKTAILS", cocktails);
        page = ConfigurationManager.getProperty("path.page.listofsuggestedcocktails.list");
        return page;
    }
}
