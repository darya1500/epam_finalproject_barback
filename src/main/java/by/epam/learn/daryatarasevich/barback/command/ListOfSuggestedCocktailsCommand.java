package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entities.Cocktail;
import by.epam.learn.daryatarasevich.barback.exception.IncorrectPasswordException;
import by.epam.learn.daryatarasevich.barback.exception.NoSuchUserException;
import by.epam.learn.daryatarasevich.barback.logic.ListOfCocktailsLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListOfSuggestedCocktailsCommand implements ActionCommand {
    private ListOfCocktailsLogic listOfCocktailsLogic=new ListOfCocktailsLogic();
    private static final Logger LOGGER = LogManager.getLogger(ListOfSuggestedCocktailsCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws NoSuchUserException, IncorrectPasswordException, NamingException {
        String page = null;
        List<Cocktail> cocktails=listOfCocktailsLogic.listSuggestedCocktails (request);
        request.setAttribute ("COCKTAILS", cocktails);
        page = ConfigurationManager.getProperty("path.page.listofsuggestedcocktails.list");
        return page;
    }
}
