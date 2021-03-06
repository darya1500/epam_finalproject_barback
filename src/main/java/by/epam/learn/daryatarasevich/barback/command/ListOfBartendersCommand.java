package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.User;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.logic.ListOfBartendersLogic;
import by.epam.learn.daryatarasevich.barback.logic.RateCocktailLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ListOfBartendersCommand implements ActionCommand {
    private ListOfBartendersLogic listOfBartendersLogic=new ListOfBartendersLogic();
    RateCocktailLogic rateCocktailLogic;
    private static final Logger LOGGER = LogManager.getLogger(ListOfBartendersCommand.class);
    /**
     * Command defines actions executed with list of bartenders from database barbackdb.users.
     * Operation LOAD loads bartender from database.
     * Operation UPDATE updates bartender in database.
     * Operation DELETE deletes bartender from database.
     * Operation LIST loads list of bartenders from database.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<User> bartenders=null;
        List<Integer> userRatings = new ArrayList<>();
        rateCocktailLogic = new RateCocktailLogic();
        String theOperation = request.getParameter ("operation");
        if (theOperation == null) {
            theOperation = "LIST";
        }
        switch (theOperation) {
            case "LOAD":
                User theBartender=listOfBartendersLogic.loadBartender (request);
                request.setAttribute("THE_BARTENDER", theBartender);
                page = ConfigurationManager.getProperty("path.page.listofbartenders.updateform");
                break;
            case "UPDATE":
                try{
                    listOfBartendersLogic.updateBartender (request);
                }catch (NullPointerException e){
                    request.setAttribute ("message",MessageManager.getProperty("message.updatingbartendererror" ));
                    LOGGER.error(MessageManager.getProperty("message.updatingbartendererror" ));
                    bartenders=listOfBartendersLogic.listBartenders (request);
                    request.setAttribute ("BARTENDERS", bartenders);
                    userRatings = new ArrayList<>();
                    for (User user : bartenders) {
                        int userID = user.getId();
                        double rating = rateCocktailLogic.getAuthorRating(userID);
                        int userRate = (int) (rating * 20);
                        userRatings.add(userRate);
                    }
                    request.setAttribute("USER_RATINGS", userRatings);
                    page = ConfigurationManager.getProperty("path.page.listofbartenders.list");
                    return page;
                }
                bartenders=listOfBartendersLogic.listBartenders (request);
                request.setAttribute ("BARTENDERS", bartenders);
                userRatings = new ArrayList<>();
                for (User user : bartenders) {
                    int userID = user.getId();
                    double rating = rateCocktailLogic.getAuthorRating(userID);
                    int userRate = (int) (rating * 20);
                    userRatings.add(userRate);
                }
                request.setAttribute("USER_RATINGS", userRatings);
                page = ConfigurationManager.getProperty("path.page.listofbartenders.list");
                break;
            case "DELETE":
                listOfBartendersLogic.deleteBartender (request);
                bartenders=listOfBartendersLogic.listBartenders(request);
                request.setAttribute ("BARTENDERS", bartenders);
                userRatings = new ArrayList<>();
                for (User user : bartenders) {
                    int userID = user.getId();
                    double rating = rateCocktailLogic.getAuthorRating(userID);
                    int userRate = (int) (rating * 20);
                    userRatings.add(userRate);
                }
                request.setAttribute("USER_RATINGS", userRatings);
                page = ConfigurationManager.getProperty("path.page.listofbartenders.list");
                break;
            case "LIST":
            default:
                bartenders=listOfBartendersLogic.listBartenders (request);
                request.setAttribute ("BARTENDERS", bartenders);
                userRatings = new ArrayList<>();
                for (User user : bartenders) {
                    int userID = user.getId();
                    double rating = rateCocktailLogic.getAuthorRating(userID);
                    int userRate = (int) (rating * 20);
                    userRatings.add(userRate);
                }
                request.setAttribute("USER_RATINGS", userRatings);
                page = ConfigurationManager.getProperty("path.page.listofbartenders.list");
        }
        return page;
    }
}
