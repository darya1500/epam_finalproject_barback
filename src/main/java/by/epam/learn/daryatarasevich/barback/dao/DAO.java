package by.epam.learn.daryatarasevich.barback.dao;

import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DAO<T> {
    private static final Logger LOGGER = LogManager.getLogger(DAO.class);

    public abstract void add(T t);

    public abstract void update(T t);

    public abstract void delete(String id);

    public abstract List<T> getAll();

    public abstract T getT(String id);

    public abstract T getT(T t);
    /**
     * To close connection, statement,result set.
     *
     * @param myConn
     * @param myStmt
     * @param myRs
     */
    public void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        } catch (SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.sqlexception"));
        }
    }


}
