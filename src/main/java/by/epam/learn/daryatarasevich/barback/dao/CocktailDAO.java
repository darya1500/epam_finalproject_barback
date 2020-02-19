package by.epam.learn.daryatarasevich.barback.dao;

import by.epam.learn.daryatarasevich.barback.entity.*;
import by.epam.learn.daryatarasevich.barback.exception.ConnectionPoolException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CocktailDAO extends DAO<Cocktail> {
    private UserDAO userDAO = new UserDAO();
    IngredientDAO ingredientDAO=new IngredientDAO();
    private static final Logger LOGGER = LogManager.getLogger(CocktailDAO.class);
    private final static String COCKTAIL_NAME_EN="cocktailNameEN";
    private final static String COCKTAIL_NAME_RU="cocktailNameRU";
    private final static String AUTHOR_ID="authorID";
    private final static String INGREDIENT_ID="ingredientID";
    private final static String INGREDIENT_AMOUNT="ingredientAmount";
    private final static String DESCRIPTION="description";
    private final static String ORDER_NUMBER="orderNumber";
    /**
     * To get all cocktails from database barbackdb.cocktails.
     *
     * @return cocktails
     */
    @Override
    public List<Cocktail> getAll() {
        List<Cocktail> cocktails = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "SELECT * FROM barbackdb.cocktails ";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            int idTemp = 0;
            List<Component> components = new ArrayList<>();
            Cocktail tempCocktail = null;
            while (myRs.next()) {
                int cocktailID = myRs.getInt("cocktailID");
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
                if (cocktailID != idTemp) {
                    if (tempCocktail != null) {
                        cocktails.add(tempCocktail);
                    }
                    components = new ArrayList<>();
                    components.add(component);
                    tempCocktail = new Cocktail(cocktailID, cocktailNameEN, cocktailNameRU, author, components);
                    idTemp = cocktailID;
                } else if (cocktailID == idTemp) {
                    components.add(component);
                    tempCocktail = new Cocktail(cocktailID, cocktailNameEN, cocktailNameRU, author, components);
                }
            }
            cocktails.add(tempCocktail);
            return cocktails;
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return cocktails;
    }
    /**
     * To delete cocktail defined by id from database barbackdb.cocktails.
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
            String sql = "delete from barbackdb.cocktails where cocktailID=?";
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
     * To get cocktail from database barbackdb.cocktails by cocktail ID.
     *
     * @param theCocktailID
     * @return cocktail
     */
    @Override
    public Cocktail getT(String theCocktailID) {
        Cocktail cocktail = null;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            int cocktailID = Integer.parseInt(theCocktailID);
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select * from barbackdb.cocktails where cocktailID=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, cocktailID);
            myRs = myStmt.executeQuery();
            List<Component> components = new ArrayList<>();
            Cocktail tempCocktail = null;
            while (myRs.next()) {
                String cocktailNameEN = myRs.getString(COCKTAIL_NAME_EN);
                String cocktailNameRU = myRs.getString(COCKTAIL_NAME_RU);
                int authorID = myRs.getInt(AUTHOR_ID);
                User author = userDAO.getT(String.valueOf(authorID));
                int ingredientID = myRs.getInt(INGREDIENT_ID);
                String ingredientAmount = myRs.getString(INGREDIENT_AMOUNT);
                String description = myRs.getString(DESCRIPTION);
                int orderNumber = myRs.getInt(ORDER_NUMBER);
                Component component = new Component(ingredientID, ingredientAmount, description, orderNumber);
                components.add(component);
                tempCocktail = new Cocktail(cocktailID, cocktailNameEN, cocktailNameRU, author, components);
            }
            cocktail = tempCocktail;
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        }  finally {
            close(myConn, myStmt, myRs);
        }
        return cocktail;
    }


    /**
     * To update cocktail in database barbackdb.cocktails.
     *
     * @param theCocktail
     */
    @Override
    public void update(Cocktail theCocktail) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "update barbackdb.cocktails "
                    + "set cocktailNameEN=?, cocktailNameRU=? "
                    + "where cocktailID=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, theCocktail.getNameEN());
            myStmt.setString(2, theCocktail.getNameRU());
            myStmt.setInt(3, theCocktail.getId());
            myStmt.execute();
        } catch (SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.sqlexception"));
        } catch (ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.connectionpoolexception"));
        } finally {
            close(myConn, myStmt, null);
        }
        Connection myConn2 = null;
        PreparedStatement myStmt2 = null;
        int length = theCocktail.getComponents().size();
        try {
            for (int i = 1; i <= length; i++) {
                myConn2 = ConnectionPool.POOL.getConnection();
                String sql = "update barbackdb.cocktails "
                        + "set ingredientID=?, ingredientAmount=?, description=? "
                        + "where cocktailID=? and orderNumber=?";
                myStmt2 = myConn2.prepareStatement(sql);
                myStmt2.setInt(1, theCocktail.getComponents().get(i - 1).getIngredientID());
                myStmt2.setString(2, theCocktail.getComponents().get(i - 1).getIngredientAmount());
                myStmt2.setString(3, theCocktail.getComponents().get(i - 1).getDescription());
                myStmt2.setInt(4, theCocktail.getId());
                myStmt2.setInt(5, i);
                myStmt2.execute();
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn2, myStmt2, null);
        }
    }
    /**
     * To add cocktail to database barbackdb.cocktails.
     *
     * @param theCocktail
     */
    @Override
    public void add(Cocktail theCocktail) {
        int previousID = getPreviousIDFromCocktails();
        ID id = new ID();
        int generatedID = id.createID(previousID);
        Connection myConn = null;
        PreparedStatement myStmt = null;
        List<Component> components = theCocktail.getComponents();
        int amount = components.size();
        Status status = theCocktail.getAuthor().getStatus();
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "insert into barbackdb.cocktails "
                    + "( cocktailID,cocktailNameEN, cocktailNameRU, authorID, ingredientID, ingredientAmount, status, description,orderNumber)"
                    + "values ( ?,?, ?, ?, ? , ?, ?, ?,? )";
            for (int i = 0; i < amount; i++) {
                myStmt = myConn.prepareStatement(sql);
                myStmt.setInt(1, generatedID);
                myStmt.setString(2, theCocktail.getNameEN());
                myStmt.setString(3, theCocktail.getNameRU());
                myStmt.setInt(4, theCocktail.getAuthor().getId());
                myStmt.setInt(5, components.get(i).getIngredientID());
                myStmt.setString(6, components.get(i).getIngredientAmount());
                if (status == Status.ADMIN) {
                    myStmt.setString(7, "APPROVED");
                } else {
                    myStmt.setString(7, "PENDING");
                }
                myStmt.setString(8, components.get(i).getDescription());
                myStmt.setInt(9, components.get(i).getOrderNumber());
                myStmt.execute();
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }
    /**
     * To get list of cocktails created by user with defined id from database barbackdb.cocktails.
     *
     * @param userID
     * @return cocktails
     */
    public List<Cocktail> getAllByID(int userID) {
        List<Cocktail> cocktails = new ArrayList<>();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "SELECT * FROM barbackdb.cocktails where authorID=? ";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, String.valueOf(userID));
            myRs = myStmt.executeQuery();
            int idTemp = 0;
            List<Component> components = new ArrayList<>();
            Cocktail tempCocktail = null;
            while (myRs.next()) {
                int cocktailID = myRs.getInt("cocktailID");
                String cocktailNameEN = myRs.getString("cocktailNameEN");
                String cocktailNameRU = myRs.getString("cocktailNameRU");
                User author = userDAO.getT(String.valueOf(userID));
                int ingredientID = myRs.getInt("ingredientID");
                String ingredientAmount = myRs.getString("ingredientAmount");
                String description = myRs.getString("description");
                String status = myRs.getString("status");
                int orderNumber = myRs.getInt("orderNumber");
                Component component = new Component(ingredientID, ingredientAmount, description, orderNumber);
                if (cocktailID != idTemp) {
                    if (tempCocktail != null) {
                        cocktails.add(tempCocktail);
                    }
                    components = new ArrayList<>();
                    components.add(component);
                    tempCocktail = new Cocktail(cocktailID, cocktailNameEN, cocktailNameRU, author, components);
                    idTemp = cocktailID;
                } else if (cocktailID == idTemp) {
                    components.add(component);
                    tempCocktail = new Cocktail(cocktailID, cocktailNameEN, cocktailNameRU, author, components);
                }
            }
            if (tempCocktail != null) {
                cocktails.add(tempCocktail);
            }
            return cocktails;
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return cocktails;
    }

    /**
     * To get maximum cocktail id number used in database barbackdb.cocktails.
     *
     * @return result
     */

    private int getPreviousIDFromCocktails() {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        List<Integer> values = new ArrayList<>();
        int result = 0;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select cocktailID from barbackdb.cocktails";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while (myRs.next()) {
                int x = myRs.getInt("cocktailID");
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
     * To add cocktail approved by administrator from barbackdb.suggestedcocktails to barbackdb.cocktails
     *
     * @param theCocktail
     */
    public void addApproved(Cocktail theCocktail) {
        int previousID = getPreviousIDFromCocktails();
        ID id = new ID();
        int generatedID = id.createID(previousID);
        Connection myConn = null;
        PreparedStatement myStmt = null;
        List<Component> components = theCocktail.getComponents();
        int amount = components.size();
        Status status = theCocktail.getAuthor().getStatus();
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "insert into barbackdb.cocktails "
                    + "( cocktailID,cocktailNameEN, cocktailNameRU, authorID, ingredientID, ingredientAmount, status, description,orderNumber)"
                    + "values ( ?,?, ?, ?, ? , ?, ?, ?,? )";
            for (int i = 0; i < amount; i++) {
                myStmt = myConn.prepareStatement(sql);
                myStmt.setInt(1, generatedID);
                myStmt.setString(2, theCocktail.getNameEN());
                myStmt.setString(3, theCocktail.getNameRU());
                myStmt.setInt(4, theCocktail.getAuthor().getId());
                myStmt.setInt(5, components.get(i).getIngredientID());
                myStmt.setString(6, components.get(i).getIngredientAmount());
                myStmt.setString(7, "APPROVED");
                myStmt.setString(8, components.get(i).getDescription());
                myStmt.setInt(9, components.get(i).getOrderNumber());
                myStmt.execute();
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }
    /**
     * Out of use method.
     */
    @Override
    public Cocktail getT(Cocktail cocktail) {
        return null;
    }
}
