package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.UserDAO;
import by.epam.learn.daryatarasevich.barback.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteAccountLogic {
    UserDAO userDAO = new UserDAO();
    private final static String MESSAGE = "Account has been deleted permanently.";
    private static final Logger LOGGER = LogManager.getLogger(DeleteAccountLogic.class);

    /**
     * To delete user from database.
     *
     * @param user
     */
    public String delete(User user) {
        userDAO.delete(String.valueOf(user.getId()));
        return MESSAGE;
    }
}

