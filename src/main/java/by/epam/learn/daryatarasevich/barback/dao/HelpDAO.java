package by.epam.learn.daryatarasevich.barback.dao;

import by.epam.learn.daryatarasevich.barback.entities.Help;
import by.epam.learn.daryatarasevich.barback.entities.HelpStatus;
import by.epam.learn.daryatarasevich.barback.entities.Ingredient;
import by.epam.learn.daryatarasevich.barback.exception.ConnectionPoolException;
import by.epam.learn.daryatarasevich.barback.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelpDAO extends DAO<Help> {

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
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            close(myConn, myStmt, null);
        }
    }


    @Override
    public void add(Help help) {

    }

    @Override
    public void update(Help help) {

    }

    @Override
    public void delete(String id) {

    }

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
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            close(myConn, myStmt, myRs);
        }
        return list;
    }

    @Override
    public Help getT(String id) {
        return null;
    }

    @Override
    public Help getT(Help help) {
        return null;
    }

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
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            close(myConn, myStmt, null);
        }
    }
}
