package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.dao.CocktailDAO;
import by.epam.learn.daryatarasevich.barback.dao.IngredientDAO;
import by.epam.learn.daryatarasevich.barback.dao.UserDAO;
import by.epam.learn.daryatarasevich.barback.entities.Cocktail;
import by.epam.learn.daryatarasevich.barback.entities.Component;
import by.epam.learn.daryatarasevich.barback.entities.Ingredient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UpdateCocktailFormCommand implements ActionCommand {
   CocktailDAO cocktailDAO;
   IngredientDAO ingredientDAO;
   UserDAO userDAO;
    private static final Logger LOGGER = LogManager.getLogger(UpdateCocktailFormCommand.class);

    @Override
    public String execute(HttpServletRequest request)  {
        String page = null;
        int cocktailID = Integer.parseInt(request.getParameter("cocktailID"));
        cocktailDAO=new CocktailDAO();
        Cocktail cocktail =cocktailDAO.getT(String.valueOf(cocktailID));
        request.setAttribute("THE_COCKTAIL",cocktail);
        request.setAttribute("cocktailNameEN",cocktail.getNameEN());
        request.setAttribute("cocktailNameRU",cocktail.getNameRU());
        request.setAttribute("authorID",cocktail.getAuthor().getId());
        userDAO=new UserDAO();
        String authorName=cocktail.getAuthor().getName();
        request.setAttribute("authorName",authorName);
        request.setAttribute("components",cocktail.getComponents());
        List<Component> components=cocktail.getComponents();
        ingredientDAO=new IngredientDAO();
        List<String> ingredientNamesEN=new ArrayList<>(10);
        List<String> ingredientNamesRU=new ArrayList<>(10);
        for (Component component:components){
            int ingredientID=component.getIngredientID();
            Ingredient ingredient=ingredientDAO.getT(String.valueOf(ingredientID));
            String ingredientNameEN=ingredient.getNameEN();
            ingredientNamesEN.add(ingredientNameEN);
            String ingredientNameRU=ingredient.getNameRU();
            ingredientNamesRU.add(ingredientNameRU);
        }
        request.setAttribute("ingredientNamesEN",ingredientNamesEN);
        request.setAttribute("ingredientNamesRU",ingredientNamesRU);
        page = ConfigurationManager.getProperty("path.page.updatecocktailform");
        return page;
    }
}