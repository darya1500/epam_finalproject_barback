package by.epam.learn.daryatarasevich.barback.dao;

import by.epam.learn.daryatarasevich.barback.exception.ConnectionPoolException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO extends DAO {
    private static final Logger LOGGER = LogManager.getLogger(RatingDAO.class);
    private final static String RATE = "rate";

    /**
     * To check in database barbackdb.rating if user defined by id has already rated cocktail defined by id.
     *
     * @param cocktailID
     * @param userIDFrom
     * @return true if user has already rated this cocktail
     */

    public boolean checkRating(String cocktailID, int userIDFrom) {
        boolean rated = false;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "SELECT * FROM barbackdb.rating WHERE cocktailID=? AND userIDFrom=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, Integer.parseInt(cocktailID));
            myStmt.setInt(2, userIDFrom);
            myRs = myStmt.executeQuery();
            if (myRs.next()) {
                rated = true;
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        }
        return rated;
    }

    /**
     * To add rating to database barbackdb.rating.
     *
     * @param userIDFrom
     * @param cocktailID
     * @param authorID
     * @param star
     */
    public void addRating(int userIDFrom, String cocktailID, String authorID, String star) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "insert into barbackdb.rating "
                    + "( userIDFrom, cocktailID, userIDTo, rate)"
                    + "values ( ?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, userIDFrom);
            myStmt.setInt(2, Integer.parseInt(cocktailID));
            myStmt.setInt(3, Integer.parseInt(authorID));
            myStmt.setInt(4, Integer.parseInt(star));
            myStmt.execute();
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }

    /**
     * To get cocktail ratings from database barbackdb.rating by cocktail id.
     *
     * @param id
     * @return ratings
     */
    public List<Integer> getAllRatings(int id) {
        List<Integer> ratings = new ArrayList<>();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select * from barbackdb.rating where cocktailID=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, id);
            myRs = myStmt.executeQuery();
            while (myRs.next()) {
                int rate = myRs.getInt(RATE);
                ratings.add(rate);
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.exceptionindatabase"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return ratings;
    }

    /**
     * To get all ratings of cocktails created by user defined by id from barbackdb.rating.
     *
     * @param authorID
     * @return ratings
     */
    public List<Integer> getAllAuthorRatings(int authorID) {
        List<Integer> ratings = new ArrayList<>();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select * from barbackdb.rating where userIDTo=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, authorID);
            myRs = myStmt.executeQuery();
            while (myRs.next()) {
                int rate = myRs.getInt("rate");
                ratings.add(rate);
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return ratings;
    }

    /**
     * Out of use method.
     */
    @Override
    public void add(Object o) {
    }

    /**
     * Out of use method.
     */
    @Override
    public void update(Object o) {
    }

    /**
     * Out of use method.
     */
    @Override
    public void delete(String id) {
    }

    /**
     * Out of use method.
     */
    @Override
    public List getAll() {
        return null;
    }

    /**
     * Out of use method.
     */
    @Override
    public Object getT(String id) {
        return null;
    }

    /**
     * Out of use method.
     */
    @Override
    public Object getT(Object o) {
        return null;
    }
}
