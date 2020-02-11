package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.RatingDAO;
import by.epam.learn.daryatarasevich.barback.entities.Cocktail;
import by.epam.learn.daryatarasevich.barback.entities.User;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.exception.RatingErrorException;
import by.epam.learn.daryatarasevich.barback.utils.AppUtils;
import by.epam.learn.daryatarasevich.barback.validation.LoginValidation;
import by.epam.learn.daryatarasevich.barback.validation.RatingValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RateCocktailLogic {
    RatingDAO ratingDAO = new RatingDAO();
    private final static String AUTHOR_ID = "authorID";
    private final static String STAR = "star";
    private final static String COCKTAIL_ID = "cocktailID";
    RatingValidator ratingValidator=new RatingValidator();
    private static final Logger LOGGER = LogManager.getLogger(RateCocktailLogic.class);

    /**
     * To check rating of cocktail.
     *
     * @param request
     * @return true or false
     */
    public boolean checkIfRated(HttpServletRequest request) {
        boolean rated;
        String cocktailID = request.getParameter(COCKTAIL_ID);
        User logedUser = AppUtils.getLogedUser(request.getSession());
        int userIDFrom = logedUser.getId();
        rated = ratingDAO.checkRating(cocktailID, userIDFrom);
        return rated;
    }

    /**
     * To rate cocktail.
     *
     * @param request
     */
    public void rate(HttpServletRequest request) throws RatingErrorException {
        User logedUser = AppUtils.getLogedUser(request.getSession());
        int userIDFrom = logedUser.getId();
        String star = request.getParameter(STAR);
        String cocktailID = request.getParameter(COCKTAIL_ID);
        String authorID = request.getParameter(AUTHOR_ID);
        boolean validated=ratingValidator.validate(star,cocktailID,authorID);
        if (validated){
            ratingDAO.addRating(userIDFrom, cocktailID, authorID, star);
        }else {
            LOGGER.error(MessageManager.getProperty("message.errorrating"));
            throw new RatingErrorException(MessageManager.getProperty("message.errorrating"));
        }
    }

    /**
     * To get cocktail ratings from database and count it.
     *
     * @param cocktail
     * @return rating
     */
    public double getCocktailRate(Cocktail cocktail) {
        double rating = 0;
        int id = cocktail.getId();
        List<Integer> list = ratingDAO.getAllRatings(id);
        if (list.size() > 0) {
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum = sum + list.get(i);
            }
            rating = sum / list.size();
        }
        return rating;
    }

    /**
     * To get user ratings from database and count it.
     *
     * @param authorID
     * @return rating
     */
    public double getAuthorRating(int authorID) {
        double rating = 0;
        ratingDAO = new RatingDAO();
        List<Integer> listOfRatings = ratingDAO.getAllAuthorRatings(authorID);
        if (listOfRatings.size() > 0) {
            double sum = 0;
            for (int i = 0; i < listOfRatings.size(); i++) {
                sum = sum + listOfRatings.get(i);
            }
            rating = sum / listOfRatings.size();
        }
        return rating;
    }
}
