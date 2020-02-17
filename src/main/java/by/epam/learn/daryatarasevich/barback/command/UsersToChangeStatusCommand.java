package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.User;
import by.epam.learn.daryatarasevich.barback.logic.UsersToChangeStatusLogic;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UsersToChangeStatusCommand implements ActionCommand {
    UsersToChangeStatusLogic usersToChangeStatusLogic=new UsersToChangeStatusLogic();
    /**
     * To get list of users and bartenders who need to change status.
     * Users can be upgraded to bartenders if they have high rating and suggested multiple cocktails.
     * Barmen can be downgraded to users if they have low ratings ang suggest small amount of cocktails.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<User> users=usersToChangeStatusLogic.findUsersToChange(request);
        List<Integer> usersRatings=usersToChangeStatusLogic.getUsersRating(users);
        List<Integer> usersNumberOfCocktails=usersToChangeStatusLogic.getUsersNumberOfCocktails(users);
        List<User> bartenders=usersToChangeStatusLogic.findBartendersToChange(request);
        List<Integer> bartendersRatings=usersToChangeStatusLogic.getUsersRating(bartenders);
        List<Integer> bartendersNumberOfCocktails=usersToChangeStatusLogic.getUsersNumberOfCocktails(bartenders);
        List<User> allUsers=new ArrayList<>();
        allUsers.addAll(users);
        allUsers.addAll(bartenders);
        List<Integer> allRatings=new ArrayList<>();
        allRatings.addAll(usersRatings);
        allRatings.addAll(bartendersRatings);
        List<Integer> allNumberOfCocktails=new ArrayList<>();
        allNumberOfCocktails.addAll(usersNumberOfCocktails);
        allNumberOfCocktails.addAll(bartendersNumberOfCocktails);
        request.setAttribute("USERS",allUsers);
        request.setAttribute("USER_RATINGS",allRatings);
        request.setAttribute("NUMBER_OF_COCKTAILS",allNumberOfCocktails);
        page = ConfigurationManager.getProperty("path.page.userstochangestatus");
        return page;
    }
}
