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

    @Override
    public void add(Object o) {
    }

    @Override
    public void update(Object o) {
    }

    @Override
    public void delete(String id) {
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object getT(String id) {
        return null;
    }

    @Override
    public Object getT(Object o) {
        return null;
    }

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
            e.printStackTrace();
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        }
        return rated;
    }

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
            e.printStackTrace();
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }
    /**
     * To get cocktail ratings from database barbackdb.rating by cocktail id.
     *
     * @param id
     * @throws SQLException,ConnectionPoolException
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
            e.printStackTrace();
        } finally {
            close(myConn, myStmt, myRs);
        }
        return ratings;
    }

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
            e.printStackTrace();
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return ratings;
    }
}
