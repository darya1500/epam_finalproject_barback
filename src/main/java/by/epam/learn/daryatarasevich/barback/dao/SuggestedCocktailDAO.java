package by.epam.learn.daryatarasevich.barback.dao;

import by.epam.learn.daryatarasevich.barback.entity.Cocktail;
import by.epam.learn.daryatarasevich.barback.entity.Component;
import by.epam.learn.daryatarasevich.barback.entity.ID;
import by.epam.learn.daryatarasevich.barback.entity.User;
import by.epam.learn.daryatarasevich.barback.exception.ConnectionPoolException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuggestedCocktailDAO extends DAO<Cocktail> {
    UserDAO userDAO = new UserDAO();
    private static final Logger LOGGER = LogManager.getLogger(SuggestedCocktailDAO.class);

    /**
     * To delte cocktail from database barbackdb.suggestedcocktails.
     *
     * @param theCocktailID
     */
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
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }

    /**
     * To get cocktails with status PENDING from database barbackdb.suggestedcocktails.
     *
     * @return cocktails
     */
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
            LOGGER.error(MessageManager.getProperty("message.sqlexception"));
            return cocktails;
        } catch (ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.connectionpoolexception"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return cocktails;
    }

    /**
     * To get cocktail defined by id from database barbackdb.suggestedcocktails.
     *
     * @param theCocktailID
     */
    public Cocktail getT(String theCocktailID) {
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
        } catch (SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.sqlexception"));
        } catch (ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.connectionpoolexception"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return cocktail;
    }

    /**
     * To change status of cocktail defined by id in database barbackdb.suggestedcocktails.
     *
     * @param theCocktailID
     */
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
        } catch (SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.sqlexception"));
        } catch (ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.connectionpoolexception"));
        } finally {
            close(myConn, myStmt, null);
        }
    }

    /**
     * To add cocktail to database barbackdb.suggestedcocktails.
     *
     * @param cocktail
     */
    public void add(Cocktail cocktail) {
        List<Component> components = cocktail.getComponents();
        int amount = components.size();
        int previousID = getPreviousIDFromSuggestedCocktails();
        ID id = new ID();
        int generatedID = id.createID(previousID);
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "insert into barbackdb.suggestedcocktails "
                    + "( cocktailNameEN, cocktailNameRU, authorID, ingredientID, ingredientAmount, description,orderNumber,id)"
                    + "values ( ?, ?, ?, ? , ?, ?, ?,? )";
            for (int i = 0; i < amount; i++) {
                myStmt = myConn.prepareStatement(sql);
                myStmt.setString(1, cocktail.getNameEN());
                myStmt.setString(2, cocktail.getNameRU());
                myStmt.setInt(3, cocktail.getAuthor().getId());
                myStmt.setInt(4, components.get(i).getIngredientID());
                myStmt.setString(5, components.get(i).getIngredientAmount());
                myStmt.setString(6, components.get(i).getDescription());
                myStmt.setInt(7, components.get(i).getOrderNumber());
                myStmt.setString(8, String.valueOf(generatedID));
                myStmt.execute();
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }

    /**
     * To get maximum id of cocktail from database barbackdb.suggestedcocktails.
     *
     * @return result
     */
    private int getPreviousIDFromSuggestedCocktails() {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        List<Integer> values = new ArrayList<>();
        int result = 0;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select id from barbackdb.suggestedcocktails";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while (myRs.next()) {
                int x = myRs.getInt("id");
                values.add(x);
            }
            result = Collections.max(values);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return result;
    }

    /**
     * Out of use method.
     */
    @Override
    public void update(Cocktail cocktail) {
    }

    /**
     * Out of use method.
     */
    @Override
    public List<Cocktail> getAll() {
        return null;
    }

    /**
     * Out of use method.
     */
    @Override
    public Cocktail getT(Cocktail cocktail) {
        return null;
    }
}
