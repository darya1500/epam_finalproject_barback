package by.epam.learn.daryatarasevich.barback.dao;

import by.epam.learn.daryatarasevich.barback.entity.Help;
import by.epam.learn.daryatarasevich.barback.entity.HelpStatus;
import by.epam.learn.daryatarasevich.barback.exception.ConnectionPoolException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelpDAO extends DAO<Help> {
    private static final Logger LOGGER = LogManager.getLogger(HelpDAO.class);

    /**
     * To add help message and information about sender to database barbackdb.help.
     *
     * @param name
     * @param email
     * @param message
     */
    public void add(String name, String email, String message) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "insert into barbackdb.Help (nameHelp,email,message) values (?,?,?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, name);
            myStmt.setString(2, email);
            myStmt.setString(3, message);
            myStmt.execute();
        } catch (SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.sqlexception"));
        } catch (ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.connectionpoolexception"));
        } finally {
            close(myConn, myStmt, null);
        }
    }

    /**
     * To get all help messages and information about senders from database barbackdb.help.
     *
     * @return list
     */

    @Override
    public List<Help> getAll() {
        List<Help> list = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "SELECT * FROM barbackdb.Help where statusHelp='PENDING'";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while (myRs.next()) {
                int id = myRs.getInt("id");
                String name = myRs.getString("nameHelp");
                String email = myRs.getString("email");
                String message = myRs.getString("message");
                Help tempHelp = new Help(id, name, email, message, HelpStatus.PENDING);
                list.add(tempHelp);
            }
            return list;
        } catch (SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.sqlexception"));
        } catch (ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.connectionpoolexception"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return list;
    }

    /**
     * To change status of message defined by id to HELPED.
     *
     * @param id
     */

    public void update(int id) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "update barbackdb.help "
                    + "set statusHelp='HELPED'"
                    + "where id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, id);
            myStmt.execute();
        } catch (SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.sqlexception"));
        } catch (ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.connectionpoolexception"));
        } finally {
            close(myConn, myStmt, null);
        }
    }

    /**
     * Out of use method.
     */
    @Override
    public void add(Help help) {
    }

    /**
     * Out of use method.
     */
    @Override
    public void update(Help help) {
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
    public Help getT(String id) {
        return null;
    }

    /**
     * Out of use method.
     */
    @Override
    public Help getT(Help help) {
        return null;
    }
}
