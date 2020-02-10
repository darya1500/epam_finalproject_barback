package by.epam.learn.daryatarasevich.barback.dao;

import by.epam.learn.daryatarasevich.barback.entities.Cocktail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DAO<T> {
    public abstract void add(T t);

    public abstract void update(T t);

    public abstract void delete(String id);

    public abstract List<T> getAll();

    public abstract T getT(String id);

    public abstract T getT(T t);

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
            e.printStackTrace();
        }
    }


}
