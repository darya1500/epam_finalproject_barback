package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.UserDAO;
import by.epam.learn.daryatarasevich.barback.entity.User;


public class LoginLogic {
    /**
     * To check user in database.
     *
     * @param email
     * @param password
     * @return user
     */
    public User check(String email, String password) {
        User user = new UserDAO().findUser(email, password);
        return user;
    }

    /**
     * To check role of user.
     *
     * @param email
     * @param password
     * @return clientType
     */
    public ClientType checkRole(String email, String password) {
        ClientType clientType = null;
        User user = new UserDAO().findUser(email, password);
        if (user != null) {
            switch (user.getStatus()) {
                case ADMIN:
                    clientType = ClientType.ADMIN;
                    break;
                case BARTENDER:
                    clientType = ClientType.BARTENDER;
                    break;
                case USER:
                    clientType = ClientType.USER;
                    break;
                default:
                    clientType = ClientType.GUEST;
            }
        }
        return clientType;
    }
}

