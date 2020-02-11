package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entities.Cocktail;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.logic.SuggestCocktailLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class SuggestCocktailCommand implements ActionCommand {
    SuggestCocktailLogic suggestCocktailLogic = new SuggestCocktailLogic();
    private static final Logger LOGGER = LogManager.getLogger(SuggestCocktailCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Cocktail cocktail = null;
        try {
            cocktail = suggestCocktailLogic.getCocktail(request);
        } catch (NullPointerException e) {
            LOGGER.error(MessageManager.getProperty("message.errorsuggestingcocktail"));
            request.setAttribute("message", MessageManager.getProperty("message.suggestcocktailerror"));
            page = ConfigurationManager.getProperty("path.page.main");
            return page;
        }
        try{
            suggestCocktailLogic.sendCocktailToAdmin(cocktail);
        }catch (NullPointerException e){
            LOGGER.error(MessageManager.getProperty("message.errorsuggestingcocktail"));
            request.setAttribute("message", MessageManager.getProperty("message.suggestcocktailerror"));
            page = ConfigurationManager.getProperty("path.page.main");
            return page;
        }
        request.setAttribute("message", MessageManager.getProperty("message.successsuggestcocktailmessage"));
        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
