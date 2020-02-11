package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.dao.UserDAO;
import by.epam.learn.daryatarasevich.barback.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class UpdateBartenderFormCommand implements ActionCommand {
    UserDAO userDAO;
    private static final Logger LOGGER = LogManager.getLogger(UpdateBartenderFormCommand.class);

    @Override
    public String execute(HttpServletRequest request)  {
        String page = null;
        int userID = Integer.parseInt(request.getParameter("userID"));
        userDAO=new UserDAO();
        User user=userDAO.getT(String.valueOf(userID));
        request.setAttribute("THE_BARTENDER",user);
        page = ConfigurationManager.getProperty("path.page.updatebartenderform");
        return page;
    }
}