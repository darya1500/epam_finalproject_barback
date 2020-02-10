package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.UserDAO;
import by.epam.learn.daryatarasevich.barback.entities.Status;
import by.epam.learn.daryatarasevich.barback.entities.User;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.validation.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListOfUsersLogic {
    private static final Logger LOGGER = LogManager.getLogger(ListOfUsersLogic.class);
    private UserDAO userDAO = new UserDAO();
    private final static String USER_NAME_EN = "userNameEN";
    private final static String USER_NAME_RU = "userNameRU";
    private final static String USER_ID = "userID";
    private final static String DESCRIPTION = "description";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";
    Validation validation=new Validation();
    /**
     * To get all users from database.
     *
     * @param request
     * @return users
     */
    public List<User> listUsers(HttpServletRequest request) {
        List<User> users = userDAO.getAll();
        return users;
    }
    /**
     * To add user to database.
     *
     * @param request
     */
    public void addUser(HttpServletRequest request) {
        String userNameEN = request.getParameter(USER_NAME_EN);
        boolean validated=validation.validateUser(userNameEN);
        if (validated) {
            String userNameRU = request.getParameter(USER_NAME_RU);
            String email = request.getParameter(EMAIL);
            String password = request.getParameter(PASSWORD);
            String description = request.getParameter(DESCRIPTION);
            User theUser = new User(userNameEN, userNameRU, email, password, description, Status.USER);
            userDAO.add(theUser);
        }else {
            LOGGER.error(MessageManager.getProperty("message.addusererror"));
            throw new NullPointerException(MessageManager.getProperty("message.addusererror" ));
        }
    }
    /**
     * To load user from database.
     *
     * @param request
     * @return theUser
     */
    public User loadUser(HttpServletRequest request) {
        String theUserID = request.getParameter(USER_ID);
        User theUser = userDAO.getT(theUserID);
        return theUser;
    }
    /**
     * To update user in database.
     *
     * @param request
     */
    public void updateUser(HttpServletRequest request) {
        int userID = Integer.parseInt(request.getParameter(USER_ID));
        String userNameEN = request.getParameter(USER_NAME_EN);
        boolean validated=validation.validateUser(userNameEN);
        if (validated) {
            String userNameRU = request.getParameter(USER_NAME_RU);
            String email = request.getParameter(EMAIL);
            String password = request.getParameter(PASSWORD);
            String description = request.getParameter(DESCRIPTION);
            User theUser = new User(userID, userNameEN, userNameRU, email, password, description, Status.USER);
            userDAO.update(theUser);
        }else {
            LOGGER.error(MessageManager.getProperty("message.updatingusererror"));
            throw new NullPointerException(MessageManager.getProperty("message.updatingusererror" ));
        }
    }
    /**
     * To delete user from database.
     *
     * @param request
     */
    public void deleteUser(HttpServletRequest request) {
        String theUserID = request.getParameter(USER_ID);
        userDAO.delete(theUserID);
    }
    /**
     * To load user from database by author ID.
     *
     * @param authorID
     * @return theUser
     */
    public User loadUserByID(int authorID) {
        User theUser = userDAO.getT(String.valueOf(authorID));
        return theUser;
    }
    /**
     * To change user defined by user ID status to new status.
     *
     * @param userID
     * @param newStatus
     */
    public void changestatus(int userID, String newStatus) {
        userDAO.changeStatus(userID,newStatus);
    }

    public User loadUserByEmail(String email) {
        User user=userDAO.getUserByEmail(email);
        return user;
    }
}
