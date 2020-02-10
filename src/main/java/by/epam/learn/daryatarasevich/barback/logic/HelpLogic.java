package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.HelpDAO;
import by.epam.learn.daryatarasevich.barback.entities.Help;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HelpLogic {
    HelpDAO helpDAO=new HelpDAO();
    private final static String HELP_ID="helpID";
    private static final Logger LOGGER = LogManager.getLogger(HelpLogic.class);

    /**
     * To add information from user who needs help to database.
     *
     * @param name
     * @param email
     * @param message
     */
    public void send(String name, String email, String message) {
        helpDAO.add(name, email, message);
    }
    /**
     * To get all information about users who need help from database.
     *
     * @return list
     *
     */
    public List<Help> getAll() {
        List<Help> list = helpDAO.getAll();
        return list;
    }
    /**
     * To update information about users who need help in database.
     *
     * @param request
     *
     */
    public void update(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(HELP_ID));
        helpDAO.update(id);
    }
}
