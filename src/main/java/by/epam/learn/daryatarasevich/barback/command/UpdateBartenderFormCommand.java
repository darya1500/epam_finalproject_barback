package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.dao.UserDAO;
import by.epam.learn.daryatarasevich.barback.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateBartenderFormCommand implements ActionCommand {
    UserDAO userDAO = new UserDAO();
    private static final Logger LOGGER = LogManager.getLogger(UpdateBartenderFormCommand.class);

    /**
     * To get bartender from database by id and to go to updatebartenderform.jsp.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        int userID = Integer.parseInt(request.getParameter("userID"));
        User user = userDAO.getT(String.valueOf(userID));
        request.setAttribute("THE_BARTENDER", user);
        page = ConfigurationManager.getProperty("path.page.updatebartenderform");
        return page;
    }
}