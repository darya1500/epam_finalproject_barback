package by.epam.learn.daryatarasevich.barback.dao;

import by.epam.learn.daryatarasevich.barback.entities.Cocktail;
import by.epam.learn.daryatarasevich.barback.entities.Component;
import by.epam.learn.daryatarasevich.barback.entities.User;
import by.epam.learn.daryatarasevich.barback.exception.ConnectionPoolException;
import by.epam.learn.daryatarasevich.barback.pool.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuggestedCocktailDAO extends DAO<Cocktail> {
    UserDAO userDAO;

    @Override
    public void add(Cocktail cocktail) {
    }

    @Override
    public void update(Cocktail cocktail) {
    }

    @Override
    public void delete(String theCocktailID) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            int cocktailID = Integer.parseInt(theCocktailID);
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "delete from barbackdb.suggestedcocktails where id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, cocktailID);
            myStmt.execute();
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            close(myConn, myStmt, null);
        }
    }


    @Override
    public List<Cocktail> getAll() {
        return null;
    }

    @Override
    public Cocktail getT(String id) {
        return null;
    }

    @Override
    public Cocktail getT(Cocktail cocktail) {
        return null;
    }

    public List<Cocktail> getSuggestedPendingCocktails() {
        userDAO = new UserDAO();
        List<Cocktail> cocktails = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "SELECT * FROM barbackdb.suggestedcocktails where status='PENDING'";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            int idTemp = 0;
            List<Component> components = new ArrayList<>();
            Cocktail tempCocktail = null;
            while (myRs.next()) {
                int id = myRs.getInt("id");
                String cocktailNameEN = myRs.getString("cocktailNameEN");
                String cocktailNameRU = myRs.getString("cocktailNameRU");
                int authorID = myRs.getInt("authorID");
                User author = userDAO.getT(String.valueOf(authorID));
                int ingredientID = myRs.getInt("ingredientID");
                String ingredientAmount = myRs.getString("ingredientAmount");
                String description = myRs.getString("description");
                int orderNumber = myRs.getInt("orderNumber");
                Component component = new Component(ingredientID, ingredientAmount, description, orderNumber);
                if (id != idTemp) {
                    if (tempCocktail != null) {
                        cocktails.add(tempCocktail);
                    }
                    components = new ArrayList<>();
                    components.add(component);
                    tempCocktail = new Cocktail(id, cocktailNameEN, cocktailNameRU, author, components);
                    idTemp = id;
                } else if (id == idTemp) {
                    components.add(component);
                    tempCocktail = new Cocktail(id, cocktailNameEN, cocktailNameRU, author, components);
                }
            }
            cocktails.add(tempCocktail);
            return cocktails;
        } catch (SQLException e) {
            e.printStackTrace();
            return cocktails;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(myConn, myStmt, myRs);
        }
        return cocktails;
    }


    public Cocktail getCocktailToApprove(String theCocktailID) {
        Cocktail cocktail = null;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int cocktailID;
        try {
            cocktailID = Integer.parseInt(theCocktailID);
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select * from barbackdb.suggestedcocktails where id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, cocktailID);
            myRs = myStmt.executeQuery();
            List<Component> components = new ArrayList<>();
            Cocktail tempCocktail = null;
            userDAO = new UserDAO();
            while (myRs.next()) {
                String cocktailNameEN = myRs.getString("cocktailNameEN");
                String cocktailNameRU = myRs.getString("cocktailNameRU");
                int authorID = myRs.getInt("authorID");
                User author = userDAO.getT(String.valueOf(authorID));
                int ingredientID = myRs.getInt("ingredientID");
                String ingredientAmount = myRs.getString("ingredientAmount");
                String description = myRs.getString("description");
                String status = myRs.getString("status");
                int orderNumber = myRs.getInt("orderNumber");
                Component component = new Component(ingredientID, ingredientAmount, description, orderNumber);
                components.add(component);
                tempCocktail = new Cocktail(cocktailID, cocktailNameEN, cocktailNameRU, author, components);
            }
            cocktail = tempCocktail;
            return cocktail;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(myConn, myStmt, myRs);
        }
        return cocktail;
    }

    public void changeStatus(String theCocktailID) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "update barbackdb.suggestedcocktails "
                    + "set status='APPROVED' "
                    + "where id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, Integer.parseInt(theCocktailID));
            myStmt.execute();
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            close(myConn, myStmt, null);
        }
    }
}
