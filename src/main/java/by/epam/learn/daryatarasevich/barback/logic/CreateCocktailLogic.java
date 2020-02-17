package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.CocktailDAO;
import by.epam.learn.daryatarasevich.barback.dao.IngredientDAO;
import by.epam.learn.daryatarasevich.barback.entity.Cocktail;
import by.epam.learn.daryatarasevich.barback.entity.Component;
import by.epam.learn.daryatarasevich.barback.entity.Ingredient;
import by.epam.learn.daryatarasevich.barback.entity.User;
import by.epam.learn.daryatarasevich.barback.exception.IngredientDBException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.validation.CommonValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CreateCocktailLogic {
    CocktailDAO cocktailDAO=new CocktailDAO();
    IngredientDAO ingredientDAO=new IngredientDAO();
    private static final Logger LOGGER = LogManager.getLogger(CreateCocktailLogic.class);
    private final static String LOGED_USER="logedUser";
    private final static String NAME_EN="nameEN";
    private final static String INGREDIENT_NAME_EN="ingredientNameEN";
    private final static String INGREDIENT_NAME_RU="ingredientNameRU";
    private final static String NAME_RU="nameRU";
    private final static String AMOUNT="amount";
    private final static String DESCRIPTION="description";
    private final static String IS_ALCOHOL="isAlcohol";
    CommonValidator commonValidator =new CommonValidator();

    public void addCocktail(Cocktail cocktail) {
        cocktailDAO.add(cocktail);
    }
    /**
     * To get cocktail by name from database.
     * To check if each ingredient is already in database. If not then to add ingredient to database.
     *
     * @param request
     * @return cocktail
     */
    public Cocktail getCocktail(HttpServletRequest request) throws IngredientDBException {
        User user = (User) request.getSession().getAttribute(LOGED_USER);
        Cocktail cocktail = null;
        String nameEN = request.getParameter(NAME_EN);
        boolean validated= commonValidator.validateCocktail(nameEN);
        if (validated){
            String nameRU = request.getParameter(NAME_RU);
            List<Component> components = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                String ingredientNameEN = request.getParameter(INGREDIENT_NAME_EN + i);
                if (!ingredientNameEN.equals("")) {
                    String ingredientNameRU = request.getParameter(INGREDIENT_NAME_RU + i);
                    String amount = request.getParameter(AMOUNT + i);
                    String description = request.getParameter(DESCRIPTION + i);
                    int order = i;
                    boolean isAction = false;
                    boolean isAlcohol;
                    String isAlc = request.getParameter(IS_ALCOHOL + i);
                    if (isAlc == null) {
                        isAlcohol = false;
                    } else {
                        isAlcohol = true;
                    }
                    int ingredientID = 0;
                    boolean isRegistered = handleIngredient(ingredientNameEN);
                    if (isRegistered) {
                        ingredientID = ingredientDAO.getID(ingredientNameEN);
                    } else {
                        Ingredient ingredient = new Ingredient(ingredientNameEN, ingredientNameRU, isAction, isAlcohol);
                        ingredientDAO.add(ingredient);
                        ingredientID = ingredientDAO.getID(ingredientNameEN);
                    }
                    Component component = new Component(ingredientID, amount, description, order);
                    components.add(component);
                }
            }
            if (components.size() >= 1) {
                cocktail = new Cocktail(nameEN, nameRU, user, components);
            }
            return cocktail;
        }else {
            LOGGER.error(MessageManager.getProperty("message.invalidnameerror"));
            throw new NullPointerException(MessageManager.getProperty("message.invalidnameerror"));
        }
    }
    /**
     * To check if ingredient is already in database.
     *
     * @param ingredientNameEN
     * @return true or false
     */
    private boolean handleIngredient(String ingredientNameEN) {
        Ingredient ingredient = ingredientDAO.checkIngredient(ingredientNameEN);
        if (ingredient == null) {
            return false;
        } else {
            return true;
        }
    }
}
