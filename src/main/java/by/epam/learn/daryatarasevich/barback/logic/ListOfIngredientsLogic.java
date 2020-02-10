package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.IngredientDAO;
import by.epam.learn.daryatarasevich.barback.entities.Cocktail;
import by.epam.learn.daryatarasevich.barback.entities.Component;
import by.epam.learn.daryatarasevich.barback.entities.Ingredient;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.validation.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ListOfIngredientsLogic {
    private IngredientDAO ingredientDAO = new IngredientDAO();
    private static final Logger LOGGER = LogManager.getLogger(ListOfIngredientsLogic.class);
    private final static String INGREDIENT_NAME_EN = "ingredientNameEN";
    private final static String INGREDIENT_NAME_RU = "ingredientNameRU";
    private final static String IS_ALCOHOL = "isAlcohol";
    private final static String INGREDIENT_ID="ingredientID";
    Validation validation=new Validation();
    /**
     * To get all ingredients from database.
     *
     * @param request
     * @return ingredients
     */
    public List<Ingredient> listIngredients(HttpServletRequest request) {
        List<Ingredient> ingredients = ingredientDAO.getAll();
        return ingredients;
    }
    /**
     * To add ingredient to database.
     *
     * @param request
     */
    public void addIngredient(HttpServletRequest request) {
        String ingredientNameEN = request.getParameter(INGREDIENT_NAME_EN);
        boolean validated=validation.validateIngredient(ingredientNameEN);
        if (validated){
            String ingredientNameRU = request.getParameter(INGREDIENT_NAME_RU);
            String isAlc = request.getParameter(IS_ALCOHOL);
            boolean alcohol;
            if (isAlc == null) {
                alcohol = false;
            } else {
                alcohol = true;
            }
            boolean action = false;
            Ingredient theIngredient = new Ingredient(ingredientNameEN, ingredientNameRU, action, alcohol);
            ingredientDAO.add(theIngredient);
        }else {
            LOGGER.error(MessageManager.getProperty("message.addingredienterror"));
            throw new NullPointerException(MessageManager.getProperty("message.addingredienterror" ));
        }

    }
    /**
     * To get ingredient from database.
     *
     * @param request
     * @return theIngredient
     */
    public Ingredient loadIngredient(HttpServletRequest request) {
        String theIngredientID = request.getParameter(INGREDIENT_ID);
        Ingredient theIngredient = ingredientDAO.getT(theIngredientID);
        return theIngredient;
    }
    /**
     * To update ingredient in database.
     *
     * @param request
     */
    public void updateIngredient(HttpServletRequest request) {
        int ingredientID = Integer.parseInt(request.getParameter(INGREDIENT_ID));
        String ingredientNameEN = request.getParameter(INGREDIENT_NAME_EN);
        boolean validated=validation.validateIngredient(ingredientNameEN);
        if (validated) {
            String ingredientNameRU = request.getParameter(INGREDIENT_NAME_RU);
            boolean isAction = false;
            boolean isAlcohol;
            String isAlc = request.getParameter(IS_ALCOHOL);
            if (isAlc == null) {
                isAlcohol = false;
            } else {
                isAlcohol = true;
            }
            Ingredient theIngredient = new Ingredient(ingredientID, ingredientNameEN, ingredientNameRU, isAction, isAlcohol);
            ingredientDAO.update(theIngredient);
        }else {
            LOGGER.error(MessageManager.getProperty("message.updatingingredienterror"));
            throw new NullPointerException(MessageManager.getProperty("message.updatingingredienterror" ));
        }
    }
    /**
     * To delete ingredient from database.
     *
     * @param request
     */
    public void deleteIngredient(HttpServletRequest request) {
        String theIngredientID = request.getParameter(INGREDIENT_ID);
        ingredientDAO.delete(theIngredientID);
    }
    /**
     * To get all ingredients from database that cocktail consists of.
     *
     * @param cocktail
     * @return ingredients
     */
    public ArrayList<Ingredient> getAllByCocktail(Cocktail cocktail) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        List<Component> components = cocktail.getComponents();
        for (int i = 0; i < components.size(); i++) {
            int ingredientID = components.get(i).getIngredientID();
            Ingredient ingredient = ingredientDAO.getT(String.valueOf(ingredientID));
            if (ingredient != null) {
                ingredients.add(ingredient);
            }
        }
        return ingredients;
    }
}
