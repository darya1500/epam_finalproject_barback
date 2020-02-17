package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.CocktailDAO;
import by.epam.learn.daryatarasevich.barback.dao.IngredientDAO;
import by.epam.learn.daryatarasevich.barback.entity.Cocktail;
import by.epam.learn.daryatarasevich.barback.entity.Component;
import by.epam.learn.daryatarasevich.barback.entity.Ingredient;
import by.epam.learn.daryatarasevich.barback.entity.User;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.validation.CommonValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SuggestCocktailLogic {
    CocktailDAO cocktailDAO = new CocktailDAO();
    IngredientDAO ingredientDAO = new IngredientDAO();
    private final static String NAME_EN = "nameEN";
    private final static String NAME_RU = "nameRU";
    private final static String INGREDIENT_NAME = "ingredientName";
    private final static String AMOUNT = "amount";
    private final static String DESCRIPTION = "description";
    private final static String LOGED_USER = "logedUser";
    CommonValidator commonValidator =new CommonValidator();
    private static final Logger LOGGER = LogManager.getLogger(SuggestCocktailLogic.class);

    /**
     * To get cocktail from database.
     *
     * @param request
     * @return cocktail
     * @throws NullPointerException
     */
    public Cocktail getCocktail(HttpServletRequest request) throws NullPointerException{
        User user = (User) request.getSession().getAttribute(LOGED_USER);
        Cocktail cocktail = null;
        String nameEN = request.getParameter(NAME_EN);
        boolean validated= commonValidator.validateCocktail(nameEN);
        if (validated){
            String nameRU = request.getParameter(NAME_RU);
            List<Component> components = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                String ingredientName = request.getParameter(INGREDIENT_NAME + i);
                if (!ingredientName.equals("")) {
                    int ingredientID = 0;
                    String amount = request.getParameter(AMOUNT + i);
                    String description = request.getParameter(DESCRIPTION + i);
                    int order = i;
                    ingredientID = handleIngredient(ingredientName);
                    Component component = new Component(ingredientID, amount, description, order);
                    components.add(component);
                }
            }
            if (components.size() >= 1) {
                cocktail = new Cocktail(nameEN, nameRU, user, components);
            }
        }else {
            LOGGER.error(MessageManager.getProperty("message.invalidnameerror"));
            throw new NullPointerException(MessageManager.getProperty("message.invalidnameerror"));
        }
        return cocktail;
    }

    /**
     * To check ingredient if it is already in database.
     * If it is not in database,it should be added.
     *
     * @param ingredientName
     * @return id
     */
    private int handleIngredient(String ingredientName) {
        int id = 0;
        Ingredient ingredient = ingredientDAO.checkIngredient(ingredientName);
        if (ingredient == null) {
            Ingredient ingredient1 = new Ingredient(ingredientName, null, false, false);
            ingredientDAO.add(ingredient1);
            id = ingredientDAO.getID(ingredientName);
        } else {
            id = ingredient.getId();
        }
        return id;
    }

    /**
     * To send cocktail to administrator.
     *
     * @param cocktail
     */
    public void sendCocktailToAdmin(Cocktail cocktail) {
           cocktailDAO.sendCocktailsForApproval(cocktail);
    }
}
