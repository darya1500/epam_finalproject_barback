package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.SuggestedCocktailDAO;
import by.epam.learn.daryatarasevich.barback.entity.Cocktail;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListOfCocktailsToApproveLogic {
    SuggestedCocktailDAO suggestedCocktailDAO=new SuggestedCocktailDAO();
    private final static String COCKTAIL_ID="cocktailID";
    /**
     * To get all cocktails from database.
     *
     * @param request
     * @return cocktails
     */
    public List<Cocktail> listCocktails(HttpServletRequest request) {
        List<Cocktail> cocktails = suggestedCocktailDAO.getSuggestedPendingCocktails();
        return cocktails;
    }
    /**
     * To get cocktail from database.
     *
     * @param request
     * @return theCocktail
     */
    public Cocktail loadCocktail(HttpServletRequest request) {
        String theCocktailID = request.getParameter(COCKTAIL_ID);
        Cocktail theCocktail = suggestedCocktailDAO.getT(theCocktailID);
        return theCocktail;
    }
    /**
     * To delete cocktail from database.
     *
     * @param request
     */
    public void deleteCocktail(HttpServletRequest request) {
        String theCocktailID = request.getParameter(COCKTAIL_ID);
        suggestedCocktailDAO.delete(theCocktailID);
    }
    /**
     * To change cocktail status in database.
     *
     * @param request
     */
    public void approveCocktail(HttpServletRequest request) {
        String theCocktailID = request.getParameter(COCKTAIL_ID);
        suggestedCocktailDAO.changeStatus(theCocktailID);
    }
}