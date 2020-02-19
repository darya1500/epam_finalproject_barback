package by.epam.learn.daryatarasevich.barback.dao;

import by.epam.learn.daryatarasevich.barback.entity.Status;
import by.epam.learn.daryatarasevich.barback.entity.User;
import by.epam.learn.daryatarasevich.barback.exception.ConnectionPoolException;
import by.epam.learn.daryatarasevich.barback.exception.InvalidStatusException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.exception.NoSuchUserException;
import by.epam.learn.daryatarasevich.barback.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO<User> {
    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);
    private final static String USER_NAME_EN = "userNameEN";
    private final static String USER_NAME_RU = "userNameRU";
    private final static String STATUS = "status";
    private final static String DESCRIPTION = "description";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";
    private final static String ADMIN = "ADMIN";
    private final static String BARTENDER = "BARTENDER";

    /**
     * To add user to database barbackdb.users.
     *
     * @param theUser
     */
    @Override
    public void add(User theUser) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "insert into barbackdb.users "
                    + "( userNameEN, userNameRU, email, password, status, description)"
                    + "values ( ?, ?, ?, ? , ?, ?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, theUser.getName());
            myStmt.setString(2, theUser.getNameRU());
            myStmt.setString(3, theUser.getEmail());
            myStmt.setString(4, theUser.getPassword());
            myStmt.setString(5, theUser.getStatus().toString());
            myStmt.setString(6, theUser.getDescription());
            myStmt.execute();
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }

    /**
     * To get all users from database barbackdb.users.
     *
     * @return userList
     */
    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "SELECT * FROM barbackdb.users WHERE status='USER'";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while (myRs.next()) {
                int id = myRs.getInt("userID");
                String name = myRs.getString("userNameEN");
                String nameRU = myRs.getString("userNameRU");
                String email = myRs.getString("email");
                String password = myRs.getString("password");
                String description = myRs.getString("description");
                User tempUser = new User(id, name, nameRU, email, password, description, Status.USER);
                userList.add(tempUser);
            }
            return userList;
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
            return userList;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    /**
     * To update user in database barbackdb.users.
     *
     * @param theUser
     */
    @Override
    public void update(User theUser) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "update barbackdb.users "
                    + "set userNameEN=?, userNameRU=?, email=?, password=? , description=? "
                    + "where userID=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, theUser.getName());
            myStmt.setString(2, theUser.getNameRU());
            myStmt.setString(3, theUser.getEmail());
            myStmt.setString(4, theUser.getPassword());
            myStmt.setString(5, theUser.getDescription());
            myStmt.setInt(6, theUser.getId());
            myStmt.execute();
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }

    /**
     * To delete user defined by id from database barbackdb.users.
     *
     * @param theUserID
     */
    @Override
    public void delete(String theUserID) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            int userID = Integer.parseInt(theUserID);
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "delete from barbackdb.users where userID=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, userID);
            myStmt.execute();
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }

    /**
     * To load user from database barbackdb.users by user iD.
     *
     * @param theUserID
     * @return theUser
     */
    @Override
    public User getT(String theUserID) {
        User theUser = null;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            int userID = Integer.parseInt(theUserID);
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select * from barbackdb.users where userID=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, userID);
            myRs = myStmt.executeQuery();
            if (myRs.next()) {
                String nameEN = myRs.getString(USER_NAME_EN);
                String nameRU = myRs.getString(USER_NAME_RU);
                String email = myRs.getString(EMAIL);
                String password = myRs.getString(PASSWORD);
                String description = myRs.getString(DESCRIPTION);
                String status = myRs.getString(STATUS);
                Status s = Status.USER;
                if (status.equalsIgnoreCase(ADMIN)) {
                    s = Status.ADMIN;
                }
                if (status.equalsIgnoreCase(BARTENDER)) {
                    s = Status.BARTENDER;
                }
                theUser = new User(userID, nameEN, nameRU, email, password, description, s);
            } else {
                LOGGER.error(MessageManager.getProperty("message.couldnotfinduserid" + userID));
                throw new NoSuchUserException(MessageManager.getProperty("message.couldnotfinduserid" + userID));
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.exceptionindatabase"));
        } catch (NoSuchUserException e) {
            LOGGER.error(MessageManager.getProperty("message.couldnotfinduserid"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return theUser;
    }

    /**
     * To get user defined by email and password from database barbackdb.users.
     *
     * @param theEmail
     * @param thePassword
     * @return result
     */
    public User findUser(String theEmail, String thePassword) {
        User result = null;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "SELECT * FROM barbackdb.users WHERE email=? AND password = ? ";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, theEmail);
            myStmt.setString(2, thePassword);
            myRs = myStmt.executeQuery();
            if (myRs.next()) {
                int userID = myRs.getInt("userID");
                String nameEN = myRs.getString("userNameEN");
                String nameRU = myRs.getString("userNameRU");
                String s = myRs.getString("status");
                Status status = null;
                switch (s) {
                    case "USER":
                        status = Status.USER;
                        break;
                    case "BARTENDER":
                        status = Status.BARTENDER;
                        break;
                    case "ADMIN":
                        status = Status.ADMIN;
                        break;
                    default:
                        throw new InvalidStatusException();
                }
                String description = myRs.getString("description");
                result = new User(userID, nameEN, nameRU, theEmail, thePassword, description, status);
            }
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } catch (InvalidStatusException e) {
            LOGGER.error(MessageManager.getProperty("message.invalidstatusexception"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return result;
    }

    /**
     * To check if database barbackdb.users contains user defined by email.
     *
     * @param email
     * @return true if database has such user already
     */
    public boolean checkUser(String email) {
        boolean result = false;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "SELECT * FROM barbackdb.users WHERE email=? ";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, email);
            myRs = myStmt.executeQuery();
            if (myRs.next()) {
                result = true;
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        }
        return result;
    }

    /**
     * To get user from database barbackdb.users.
     * User includes information about user and id,unlike userX that includes only information about user.
     *
     * @param userX
     * @return user
     */
    @Override
    public User getT(User userX) {
        User user = null;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select * from barbackdb.users where email=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, userX.getEmail());
            myRs = myStmt.executeQuery();
            if (myRs.next()) {
                int userID = myRs.getInt("userID");
                String nameEN = myRs.getString("userNameEN");
                String nameRU = myRs.getString("userNameRU");
                String email = myRs.getString("email");
                String password = myRs.getString("password");
                String description = myRs.getString("description");
                user = new User(userID, nameEN, nameRU, email, password, description, Status.USER);
            }
            return user;
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return user;
    }

    /**
     * To get all users from database barbackdb.users whose status is BARTENDER.
     *
     * @return bartenderList
     */
    public List<User> getBartenders() {
        List<User> bartenderList = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "SELECT * FROM barbackdb.users WHERE status='BARTENDER'";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while (myRs.next()) {
                int id = myRs.getInt("userID");
                String name = myRs.getString("userNameEN");
                String nameRU = myRs.getString("userNameRU");
                String email = myRs.getString("email");
                String password = myRs.getString("password");
                String description = myRs.getString("description");
                User tempUser = new User(id, name, nameRU, email, password, description, Status.BARTENDER);
                bartenderList.add(tempUser);
            }
            return bartenderList;
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return bartenderList;
    }

    /**
     * To change status of user defined by user ID to new status.
     *
     * @param userID
     * @param newStatus
     */
    public void changeStatus(int userID, String newStatus) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "update barbackdb.users "
                    + "set status=? "
                    + "where userID=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, newStatus);
            myStmt.setInt(2, userID);
            myStmt.execute();
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }

    /**
     * To get user from database barbackdb.users by defined email.
     *
     * @param email
     * @return theUser
     */
    public User getUserByEmail(String email) {
        User theUser = null;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select * from barbackdb.users where email=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, email);
            myRs = myStmt.executeQuery();
            if (myRs.next()) {
                int userID = myRs.getInt("userID");
                String nameEN = myRs.getString(USER_NAME_EN);
                String nameRU = myRs.getString(USER_NAME_RU);
                String password = myRs.getString(PASSWORD);
                String description = myRs.getString(DESCRIPTION);
                String status = myRs.getString(STATUS);
                Status s = Status.USER;
                if (status.equalsIgnoreCase(ADMIN)) {
                    s = Status.ADMIN;
                }
                if (status.equalsIgnoreCase(BARTENDER)) {
                    s = Status.BARTENDER;
                }
                theUser = new User(userID, nameEN, nameRU, email, password, description, s);
            } else {
                LOGGER.error(MessageManager.getProperty("message.couldnotfindusername"));
                throw new NoSuchUserException(MessageManager.getProperty("message.couldnotfindusername"));
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.exceptionindatabase"));
        } catch (NoSuchUserException e) {
            LOGGER.error(MessageManager.getProperty("message.couldnotfindusername"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return theUser;
    }
}
