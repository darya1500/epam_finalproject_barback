package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entities.User;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.logic.ListOfUsersLogic;
import by.epam.learn.daryatarasevich.barback.logic.RateCocktailLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ListOfUsersCommand implements ActionCommand {
    private ListOfUsersLogic listOfUsersLogic = new ListOfUsersLogic();
    RateCocktailLogic rateCocktailLogic;
    private static final Logger LOGGER = LogManager.getLogger(ListOfUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<User> users = null;
        List<Integer> userRatings = new ArrayList<>();
        rateCocktailLogic = new RateCocktailLogic();
        String theOperation = request.getParameter("operation");
        if (theOperation == null) {
            theOperation = "LIST";
        }
        switch (theOperation) {
            case "LIST":
                users = listOfUsersLogic.listUsers(request);
                request.setAttribute("USERS", users);
                userRatings = new ArrayList<>();
                for (User user : users) {
                    int userID = user.getId();
                    double rating = rateCocktailLogic.getAuthorRating(userID);
                    int userRate = (int) (rating * 20);
                    userRatings.add(userRate);
                }
                request.setAttribute("USER_RATINGS", userRatings);
                page = ConfigurationManager.getProperty("path.page.listofusers.list");
                break;
            case "ADD":
               try {
                   listOfUsersLogic.addUser(request);
               }catch (NullPointerException e){
                   request.setAttribute ("message", MessageManager.getProperty("message.addusererror" ));
                   LOGGER.error(MessageManager.getProperty("message.addusererror" ));
                   users = listOfUsersLogic.listUsers(request);
                   request.setAttribute("USERS", users);
                   userRatings = new ArrayList<>();
                   for (User user : users) {
                       int userID = user.getId();
                       double rating = rateCocktailLogic.getAuthorRating(userID);
                       int userRate = (int) (rating * 20);
                       userRatings.add(userRate);
                   }
                   request.setAttribute("USER_RATINGS", userRatings);
                   page = ConfigurationManager.getProperty("path.page.listofusers.list");
                   return page;
               }
                users = listOfUsersLogic.listUsers(request);
                request.setAttribute("USERS", users);
                userRatings = new ArrayList<>();
                for (User user : users) {
                    int userID = user.getId();
                    double rating = rateCocktailLogic.getAuthorRating(userID);
                    int userRate = (int) (rating * 20);
                    userRatings.add(userRate);
                }
                request.setAttribute("USER_RATINGS", userRatings);
                page = ConfigurationManager.getProperty("path.page.listofusers.list");
                break;
            case "LOAD":
                User theUser = listOfUsersLogic.loadUser(request);
                request.setAttribute("THE_USER", theUser);
                page = ConfigurationManager.getProperty("path.page.listofusers.updateform");
                break;
            case "UPDATE":
                try{
                    listOfUsersLogic.updateUser(request);
                }catch (NullPointerException e){
                    request.setAttribute ("message", MessageManager.getProperty("message.updatingusererror" ));
                    LOGGER.error(MessageManager.getProperty("message.updatingusererror" ));
                    users = listOfUsersLogic.listUsers(request);
                    request.setAttribute("USERS", users);
                    userRatings = new ArrayList<>();
                    for (User user : users) {
                        int userID = user.getId();
                        double rating = rateCocktailLogic.getAuthorRating(userID);
                        int userRate = (int) (rating * 20);
                        userRatings.add(userRate);
                    }
                    request.setAttribute("USER_RATINGS", userRatings);
                    page = ConfigurationManager.getProperty("path.page.listofusers.list");
                    return page;
                }

                users = listOfUsersLogic.listUsers(request);
                request.setAttribute("USERS", users);
                userRatings = new ArrayList<>();
                for (User user : users) {
                    int userID = user.getId();
                    double rating = rateCocktailLogic.getAuthorRating(userID);
                    int userRate = (int) (rating * 20);
                    userRatings.add(userRate);
                }
                request.setAttribute("USER_RATINGS", userRatings);
                page = ConfigurationManager.getProperty("path.page.listofusers.list");
                break;
            case "DELETE":
                listOfUsersLogic.deleteUser(request);
                users = listOfUsersLogic.listUsers(request);
                request.setAttribute("USERS", users);
                userRatings = new ArrayList<>();
                for (User user : users) {
                    int userID = user.getId();
                    double rating = rateCocktailLogic.getAuthorRating(userID);
                    int userRate = (int) (rating * 20);
                    userRatings.add(userRate);
                }
                request.setAttribute("USER_RATINGS", userRatings);
                page = ConfigurationManager.getProperty("path.page.listofusers.list");
                break;
            default:
                users = listOfUsersLogic.listUsers(request);
                request.setAttribute("USERS", users);
                userRatings = new ArrayList<>();
                for (User user : users) {
                    int userID = user.getId();
                    double rating = rateCocktailLogic.getAuthorRating(userID);
                    int userRate = (int) (rating * 20);
                    userRatings.add(userRate);
                }
                request.setAttribute("USER_RATINGS", userRatings);
                page = ConfigurationManager.getProperty("path.page.listofusers.list");
        }
        return page;
    }
}
