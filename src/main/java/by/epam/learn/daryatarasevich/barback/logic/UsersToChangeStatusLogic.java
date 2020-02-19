package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.CocktailDAO;
import by.epam.learn.daryatarasevich.barback.dao.RatingDAO;
import by.epam.learn.daryatarasevich.barback.dao.UserDAO;
import by.epam.learn.daryatarasevich.barback.entity.Cocktail;
import by.epam.learn.daryatarasevich.barback.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UsersToChangeStatusLogic {
    UserDAO userDAO = new UserDAO();
    CocktailDAO cocktailDAO = new CocktailDAO();
    RatingDAO ratingDAO = new RatingDAO();

    /**
     * To get list of users from database who need to change status.
     * To get number of created cocktails by each user.
     * To get average rating of each user.
     * If user created 5 or more cocktails and users rating is 4 stars or more he will be added to users.
     *
     * @param request
     * @return users
     */
    public List<User> findUsersToChange(HttpServletRequest request) {
        List<User> users = new ArrayList<>();
        List<User> allUsers = userDAO.getAll();
        for (User user : allUsers) {
            List<Cocktail> cocktails = cocktailDAO.getAllByID(user.getId());
            int numberOfCocktails = cocktails.size();
            if (numberOfCocktails >= 5) {
                List<Integer> list = ratingDAO.getAllAuthorRatings(user.getId());
                double rating = 0;
                if (list.size() > 0) {
                    int sum = 0;
                    for (int i = 0; i < list.size(); i++) {
                        sum = sum + list.get(i);
                    }
                    rating = sum / list.size();
                }
                int rate = (int) (rating * 20);
                if (rate >= 80) {
                    users.add(user);
                }
            }
        }
        return users;
    }

    /**
     * To get list of ratings of each user from list of users from database.
     *
     * @param users
     * @return ratings
     */
    public List<Integer> getUsersRating(List<User> users) {
        List<Integer> ratings = new ArrayList<>();
        for (User user : users) {
            List<Integer> list = ratingDAO.getAllAuthorRatings(user.getId());
            double rating = 0;
            if (list.size() > 0) {
                int sum = 0;
                for (int i = 0; i < list.size(); i++) {
                    sum = sum + list.get(i);
                }
                rating = sum / list.size();
            }
            int rate = (int) (rating * 20);
            ratings.add(rate);
        }
        return ratings;
    }

    /**
     * To get list of number of cocktails of each user from list of users from database.
     *
     * @param users
     * @return numberOfCocktails
     */
    public List<Integer> getUsersNumberOfCocktails(List<User> users) {
        List<Integer> numberOfCocktails = new ArrayList<>();
        for (User user : users) {
            List<Cocktail> cocktails = cocktailDAO.getAllByID(user.getId());
            int number = cocktails.size();
            numberOfCocktails.add(number);
        }
        return numberOfCocktails;
    }

    /**
     * To get list of bartender from database who need to change status.
     * To get number of created cocktails by each bartender.
     * To get average rating of each bartender.
     * If bartender created less than 5 cocktails and bartenders rating is 1 staror lesse he will be added to bartenders.
     *
     * @param request
     * @return bartenders
     */
    public List<User> findBartendersToChange(HttpServletRequest request) {
        List<User> bartenders = new ArrayList<>();
        List<User> allBartenders = userDAO.getBartenders();
        for (User bartender : allBartenders) {
            List<Cocktail> cocktails = cocktailDAO.getAllByID(bartender.getId());
            int numberOfCocktails = cocktails.size();
            if (numberOfCocktails < 5) {
                List<Integer> list = ratingDAO.getAllAuthorRatings(bartender.getId());
                double rating = 0;
                if (list.size() > 0) {
                    int sum = 0;
                    for (int i = 0; i < list.size(); i++) {
                        sum = sum + list.get(i);
                    }
                    rating = sum / list.size();
                }
                int rate = (int) (rating * 20);
                if (rate <= 20) {
                    bartenders.add(bartender);
                }
            }
        }
        return bartenders;
    }
}
