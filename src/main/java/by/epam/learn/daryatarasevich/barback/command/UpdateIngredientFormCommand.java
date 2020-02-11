package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.dao.IngredientDAO;
import by.epam.learn.daryatarasevich.barback.entities.Ingredient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class UpdateIngredientFormCommand implements ActionCommand {
    IngredientDAO ingredientDAO=new IngredientDAO();
    private static final Logger LOGGER = LogManager.getLogger(UpdateIngredientFormCommand.class);
    /**
     * To redirect to ingredient update form.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request)  {
        String page = null;
        int ingredientID = Integer.parseInt(request.getParameter("ingredientID"));
        Ingredient ingredient=ingredientDAO.getT(String.valueOf(ingredientID));
        request.setAttribute("THE_INGREDIENT",ingredient);
        page = ConfigurationManager.getProperty("path.page.updateingredientform");
        return page;
}
}
