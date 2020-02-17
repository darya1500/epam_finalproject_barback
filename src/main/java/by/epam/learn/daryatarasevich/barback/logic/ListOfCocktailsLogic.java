package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.CocktailDAO;
import by.epam.learn.daryatarasevich.barback.dao.IngredientDAO;
import by.epam.learn.daryatarasevich.barback.dao.UserDAO;
import by.epam.learn.daryatarasevich.barback.entity.Cocktail;
import by.epam.learn.daryatarasevich.barback.entity.Component;
import by.epam.learn.daryatarasevich.barback.entity.Ingredient;
import by.epam.learn.daryatarasevich.barback.entity.User;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.util.AppUtils;
import by.epam.learn.daryatarasevich.barback.validation.CommonValidator;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ListOfCocktailsLogic {
    private CocktailDAO cocktailDAO = new CocktailDAO();
    IngredientDAO ingredientDAO=new IngredientDAO();
    UserDAO userDAO=new UserDAO();
    CommonValidator commonValidator =new CommonValidator();
    private final static String COCKTAIL_NAME_EN="cocktailNameEN";
    private final static String COCKTAIL_NAME_RU="cocktailNameRU";
    private final static String COCKTAIL_ID="cocktailID";
    private final static String INGREDIENT_ID="ingredientID";
    private final static String INGREDIENT_AMOUNT="ingredientAmount";
    private final static String DESCRIPTION="description";
    private final static String ORDER_NUMBER="orderNumber";
    private final static String INGREDIENT_NAME_EN = "ingredientNameEN";
    private final static String INGREDIENT_NAME_RU = "ingredientNameRU";
    private final static String IS_ALCOHOL = "isAlcohol";
    private final static String AUTHOR_ID = "authorID";
    private final static String NUMBER_OF_INGREDIENTS="numberOfIngredients";
    private final static String LOGED_USER="logedUser";

    /**
     * To get all cocktails from database.
     *
     * @param request
     * @return cocktails
     *
     */
    public List<Cocktail> listCocktails(HttpServletRequest request) {
        List<Cocktail> cocktails = cocktailDAO.getAll();
        return cocktails;
    }
    /**
     * To add cocktail to database.
     *
     * @param request
     *
     */
    public void addCocktail(HttpServletRequest request) throws NamingException {
        int numberOfIngredients = Integer.parseInt(request.getParameter(NUMBER_OF_INGREDIENTS));
        String cocktailNameEN = request.getParameter(COCKTAIL_NAME_EN);
        boolean validated= commonValidator.validateCocktail(cocktailNameEN);
        if (validated){
            String cocktailNameRU = request.getParameter(COCKTAIL_NAME_RU);
            User author = AppUtils.getLogedUser(request.getSession());
            List<Component> components = new ArrayList<>();
            for (int i = 1; i <= numberOfIngredients; i++) {
                String ingredientNameEN = request.getParameter(INGREDIENT_NAME_EN + i);
                String ingredientNameRU = request.getParameter(INGREDIENT_NAME_RU + i);
                String ingredientAmount = request.getParameter(INGREDIENT_AMOUNT + i);
                String description = request.getParameter(DESCRIPTION + i);
                int orderNumber = Integer.parseInt(request.getParameter(ORDER_NUMBER + i));
                int ingredientID = handleIngredient(ingredientNameEN, ingredientNameRU);
                Component component = new Component(ingredientID, ingredientAmount, description, orderNumber);
                components.add(component);
            }
            Cocktail theCocktail = new Cocktail(cocktailNameEN, cocktailNameRU, author, components);
            cocktailDAO.add(theCocktail);
        }else{
            throw new NullPointerException(MessageManager.getProperty("message.addingcocktailerror" ));
        }
    }
    /**
     * To find and load cocktail from database by cocktail ID.
     *
     * @param request
     * @return theCocktail
     */
    public Cocktail loadCocktail(HttpServletRequest request) {
        String theCocktailID = request.getParameter(COCKTAIL_ID);
       boolean validated= commonValidator.validateID(theCocktailID);
       if (validated){
           Cocktail theCocktail = cocktailDAO.getT(theCocktailID);
           return theCocktail;
       }else {
           return null;
       }
    }
    /**
     * To update cocktail in database.
     *
     * @param request
     */
    public void updateCocktail(HttpServletRequest request) {
        List<Component> components = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String ingredientAmount = request.getParameter(INGREDIENT_AMOUNT + i);
            String description = request.getParameter(DESCRIPTION + i);
            int orderNumber = i;
            String ingredientID = request.getParameter(INGREDIENT_ID + i);
            String ingredientNameENNew = request.getParameter(INGREDIENT_NAME_EN + i);
            if (ingredientNameENNew == null) {
                break;
            }
            String ingredientNameRUNew = request.getParameter(INGREDIENT_NAME_RU + i);
            Ingredient ingredient = ingredientDAO.getT(ingredientID);
            String ingredientNameENOld = ingredient.getNameEN();
            String ingredientNameRUOld = ingredient.getNameRU();
            int id;
            if (ingredientNameENNew.equalsIgnoreCase(ingredientNameENOld) && ingredientNameRUNew.equalsIgnoreCase(ingredientNameRUOld)) {
                id = Integer.parseInt(ingredientID);
            } else {
                Ingredient newIngredient = new Ingredient(ingredientNameENNew, ingredientNameRUNew, false, false);
                ingredientDAO.add(newIngredient);
                Ingredient newIngredientWithId = ingredientDAO.getT(newIngredient);
                id = newIngredientWithId.getId();
            }
            Component component = new Component(id, ingredientAmount, description, orderNumber);
            components.add(component);
        }
        String cocktailNameEN = request.getParameter(COCKTAIL_NAME_EN);
        boolean validated= commonValidator.validateCocktail(cocktailNameEN);
        if (validated){
            String cocktailNameRU = request.getParameter(COCKTAIL_NAME_RU);
            int authorID = Integer.parseInt(request.getParameter(AUTHOR_ID));
            userDAO = new UserDAO();
            User author = userDAO.getT(String.valueOf(authorID));
            int cocktailID = Integer.parseInt(request.getParameter(COCKTAIL_ID));
            Cocktail theCocktail = new Cocktail(cocktailID, cocktailNameEN, cocktailNameRU, author, components);
            cocktailDAO = new CocktailDAO();
            cocktailDAO.update(theCocktail);
        }else{
            throw new NullPointerException(MessageManager.getProperty("message.updatingcocktailerror" ));
        }
    }
    /**
     * To delete cocktail from database.
     *
     * @param request
     */
    public void deleteCocktail(HttpServletRequest request) {
        String theCocktailID = request.getParameter(COCKTAIL_ID);
        cocktailDAO.delete(theCocktailID);
    }
    /**
     * To ckeck ingredient if it exists by name.
     * If ingredient does not exist then to create it.
     *
     * @param ingredientNameEN
     * @param ingredientNameRU
     * @return ingredientID
     */
    private int handleIngredient(String ingredientNameEN, String ingredientNameRU) throws NamingException {
        int ingredientID = 0;
        boolean isRegistered = cocktailDAO.checkIngredient(ingredientNameEN);
        if (isRegistered) {
            ingredientID = cocktailDAO.getIngredientID(ingredientNameEN);
        } else {
            cocktailDAO.addIngredient(ingredientNameEN, ingredientNameRU);
            ingredientID = cocktailDAO.getIngredientID(ingredientNameEN);
        }
        return ingredientID;
    }
    /**
     * To get all suggested cocktails from database.
     *
     * @param request
     * @return cocktails
     */
    public List<Cocktail> listSuggestedCocktails(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(LOGED_USER);
        int userID = user.getId();
        List<Cocktail> cocktails = cocktailDAO.getAllByID(userID);
        return cocktails;
    }

    /**
     * To add approved cocktail to database.
     *
     * @param cocktail
     */
    public void addCocktailApproved(Cocktail cocktail) {
        cocktailDAO.addApproved(cocktail);
    }
}
