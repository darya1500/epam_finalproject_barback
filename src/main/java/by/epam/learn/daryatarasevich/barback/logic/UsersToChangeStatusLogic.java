package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.CocktailDAO;
import by.epam.learn.daryatarasevich.barback.dao.RatingDAO;
import by.epam.learn.daryatarasevich.barback.dao.UserDAO;
import by.epam.learn.daryatarasevich.barback.entities.Cocktail;
import by.epam.learn.daryatarasevich.barback.entities.User;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UsersToChangeStatusLogic {
    UserDAO userDAO=new UserDAO();
    CocktailDAO cocktailDAO=new CocktailDAO();
    RatingDAO ratingDAO=new RatingDAO();

    public List<User> findUsersToChange(HttpServletRequest request) {
        List<User> users=new ArrayList<>();
        List<User> allUsers=userDAO.getAll();
        for (User user:allUsers){
            List<Cocktail> cocktails=cocktailDAO.getAllByID(user.getId());
            int numberOfCocktails=cocktails.size();
            if (numberOfCocktails>=5){
                List<Integer> list=ratingDAO.getAllAuthorRatings(user.getId());
                double rating = 0;
                if (list.size() > 0) {
                    int sum = 0;
                    for (int i = 0; i < list.size(); i++) {
                        sum = sum + list.get(i);
                    }
                    rating = sum / list.size();
                }
                int rate= (int) (rating*20);
              if (rate>=80){
                  users.add(user);
              }
            }
        }
        return users;
    }

    public List<Integer> getUsersRating(List<User> users) {
        List<Integer> ratings=new ArrayList<>();
       for (User user:users){
           List<Integer> list=ratingDAO.getAllAuthorRatings(user.getId());
           double rating = 0;
           if (list.size() > 0) {
               int sum = 0;
               for (int i = 0; i < list.size(); i++) {
                   sum = sum + list.get(i);
               }
               rating = sum / list.size();
           }
           int rate= (int) (rating*20);
           ratings.add(rate);
       }
        return ratings;
    }

    public List<Integer> getUsersNumberOfCocktails(List<User> users) {
        List<Integer> numberOfCocktails=new ArrayList<>();
        for (User user:users){
            List<Cocktail> cocktails=cocktailDAO.getAllByID(user.getId());
            int number=cocktails.size();
            numberOfCocktails.add(number);
        }
        return numberOfCocktails;
    }

    public List<User> findBartendersToChange(HttpServletRequest request) {
        List<User> bartenders=new ArrayList<>();
        List<User> allBartenders=userDAO.getBartenders();
        for (User bartender:allBartenders){
            List<Cocktail> cocktails=cocktailDAO.getAllByID(bartender.getId());
            int numberOfCocktails=cocktails.size();
            if (numberOfCocktails<=5){
                List<Integer> list=ratingDAO.getAllAuthorRatings(bartender.getId());
                double rating = 0;
                if (list.size() > 0) {
                    int sum = 0;
                    for (int i = 0; i < list.size(); i++) {
                        sum = sum + list.get(i);
                    }
                    rating = sum / list.size();
                }
                int rate= (int) (rating*20);
                if (rate<=20){
                    bartenders.add(bartender);
                }
            }
        }
        return bartenders;
    }
}
