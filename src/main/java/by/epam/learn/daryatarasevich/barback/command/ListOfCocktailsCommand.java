package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.Cocktail;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.logic.ListOfCocktailsLogic;
import by.epam.learn.daryatarasevich.barback.logic.RateCocktailLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ListOfCocktailsCommand implements ActionCommand {
    private ListOfCocktailsLogic listOfCocktailsLogic=new ListOfCocktailsLogic();
    RateCocktailLogic rateCocktailLogic;
    private static final Logger LOGGER = LogManager.getLogger(ListOfCocktailsCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws NamingException {
        String page = null;
        List<Cocktail> cocktails=null;
        String theOperation = request.getParameter ("operation");
        if (theOperation == null) {
            theOperation = "LIST";
        }
        switch (theOperation) {
            case "ADD":
               try {
                   listOfCocktailsLogic.addCocktail (request);
               }catch (NullPointerException e){
                   request.setAttribute ("message", MessageManager.getProperty("message.addingcocktailerror"));
                   LOGGER.error(MessageManager.getProperty("message.addingcocktailerror"));
                   cocktails=listOfCocktailsLogic.listCocktails (request);
                   request.setAttribute ("COCKTAILS", cocktails);
                   page = ConfigurationManager.getProperty("path.page.listofcocktails.list");
                   return page;
               }
                cocktails=listOfCocktailsLogic.listCocktails(request);
                request.setAttribute ("COCKTAILS", cocktails);
                page = ConfigurationManager.getProperty("path.page.listofcocktails.list");
                break;
            case "LOAD":
                Cocktail cocktail=listOfCocktailsLogic.loadCocktail (request);
                request.setAttribute("THE_COCKTAIL", cocktail);
                page = ConfigurationManager.getProperty("path.page.listofcocktails.updateform");
                break;
            case "UPDATE":
              try{
                  listOfCocktailsLogic.updateCocktail(request);
              }catch (NullPointerException e){
                  request.setAttribute ("message", MessageManager.getProperty("message.updatingcocktailerror"));
                  LOGGER.error(MessageManager.getProperty("message.updatingcocktailerror"));
                  cocktails=listOfCocktailsLogic.listCocktails (request);
                  request.setAttribute ("COCKTAILS", cocktails);
                  page = ConfigurationManager.getProperty("path.page.listofcocktails.list");
                  return page;
              }
                cocktails=listOfCocktailsLogic.listCocktails (request);
                request.setAttribute ("COCKTAILS", cocktails);
                page = ConfigurationManager.getProperty("path.page.listofcocktails.list");
                break;
            case "DELETE":
                listOfCocktailsLogic.deleteCocktail (request);
                cocktails=listOfCocktailsLogic.listCocktails (request);
                request.setAttribute ("COCKTAILS", cocktails);
                page = ConfigurationManager.getProperty("path.page.listofcocktails.list");
                break;
            case "LIST":
            default:
                cocktails=listOfCocktailsLogic.listCocktails (request);
                request.setAttribute ("COCKTAILS", cocktails);
                page = ConfigurationManager.getProperty("path.page.listofcocktails.list");
        }
        rateCocktailLogic=new RateCocktailLogic();
        List<Cocktail> cocktails2=listOfCocktailsLogic.listCocktails (request);
        List<Integer> ratings=new ArrayList<>();
        for (Cocktail cocktail:cocktails2){
            int cocktailRating= (int) (rateCocktailLogic.getCocktailRate(cocktail)*20);
            ratings.add(cocktailRating);
        }
        request.setAttribute ("COCKTAILS_RATINGS", ratings);
        return page;
    }
}
