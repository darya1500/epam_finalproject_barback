package by.epam.learn.daryatarasevich.barback.dao;

import by.epam.learn.daryatarasevich.barback.entity.Ingredient;
import by.epam.learn.daryatarasevich.barback.exception.*;
import by.epam.learn.daryatarasevich.barback.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO extends DAO<Ingredient> {
    private static final Logger LOGGER = LogManager.getLogger(IngredientDAO.class);
    private final static String INGREDIENT_NAME_EN = "ingredientNameEN";
    private final static String INGREDIENT_NAME_RU = "ingredientNameRU";
    private final static String IS_ALCOHOL = "isAlcohol";
    private final static String IS_ACTION = "isAction";
    private final static String INGREDIENT_ID="ingredientID";

    /**
     * To add ingredient to database barbackdb.ingredients.
     *
     * @param theIngredient
     */
    @Override
    public void add(Ingredient theIngredient) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "insert into barbackdb.ingredients "
                    + "( ingredientNameEN, ingredientNameRU, isAlcohol, isAction)"
                    + "values ( ?, ?, ?, ? )";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, theIngredient.getNameEN());
            myStmt.setString(2, theIngredient.getNameRU());
            myStmt.setBoolean(3, theIngredient.isAlcohol());
            myStmt.setBoolean(4, theIngredient.isAction());
            myStmt.execute();
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }
    /**
     * To update ingredient in database barbackdb.ingredients.
     *
     * @param theIngredient
     */
    @Override
    public void update(Ingredient theIngredient) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "update barbackdb.ingredients "
                    + "set ingredientNameEN=?, ingredientNameRU=?, isAlcohol=?, isAction=? "
                    + "where ingredientID=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, theIngredient.getNameEN());
            myStmt.setString(2, theIngredient.getNameRU());
            myStmt.setBoolean(3, theIngredient.isAlcohol());
            myStmt.setBoolean(4, theIngredient.isAction());
            myStmt.setInt(5, theIngredient.getId());
            myStmt.execute();
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }
    /**
     * To delete ingredient defined by id from database barbackdb.ingredients.
     *
     * @param theIngredientID
     */
    @Override
    public void delete(String theIngredientID) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            int ingredientID = Integer.parseInt(theIngredientID);
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "delete from barbackdb.ingredients where ingredientID=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, ingredientID);
            myStmt.execute();
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }
    /**
     * To get all ingredients from database barbackdb.ingredients.
     *
     * @return ingredientList
     */
    @Override
    public List<Ingredient> getAll() {
        List<Ingredient> ingredientList = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "SELECT * FROM barbackdb.ingredients";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while (myRs.next()) {
                int ingredientID = myRs.getInt("ingredientID");
                String ingredientNameEN = myRs.getString("ingredientNameEN");
                String ingredientNameRU = myRs.getString("ingredientNameRU");
                boolean alcohol = myRs.getBoolean("isAlcohol");
                boolean action = myRs.getBoolean("isAction");
                Ingredient tempIngredient = new Ingredient(ingredientID, ingredientNameEN, ingredientNameRU, action, alcohol);
                ingredientList.add(tempIngredient);
            }
            return ingredientList;
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return ingredientList;
    }
    /**
     * To get all ingredients from database barbackdb.ingredients that cocktail defined by id consists of.
     *
     * @param theIngredientID
     * @return theIngredient
     */
    @Override
    public Ingredient getT(String theIngredientID) {
        Ingredient theIngredient = null;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
           int ingredientID = Integer.parseInt(theIngredientID);
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select * from barbackdb.ingredients where ingredientID=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, ingredientID);
            myRs = myStmt.executeQuery();
            if (myRs.next()) {
                String ingredientNameEN = myRs.getString(INGREDIENT_NAME_EN);
                String ingredientNameRU = myRs.getString(INGREDIENT_NAME_RU);
                boolean alcohol = myRs.getBoolean(IS_ALCOHOL);
                boolean action = myRs.getBoolean(IS_ACTION);
                theIngredient = new Ingredient(ingredientID, ingredientNameEN, ingredientNameRU, alcohol, action);
            } else {
                LOGGER.error(MessageManager.getProperty("message.couldnotfindingredientid" + ingredientID));
                throw new NoSuchIngredientsException(MessageManager.getProperty("message.couldnotfindingredientid"+ ingredientID));
            }
        } catch (SQLException | ConnectionPoolException | NoSuchIngredientsException e) {
            LOGGER.error(MessageManager.getProperty("message.exceptionindatabase"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return theIngredient;
    }
    /**
     * To get ingredient from database barbackdb.ingredients.
     * TheIngredient contains all information about ingredient and its id of ingredient,unlike ingredient that does not contain id.
     *
     * @param ingredient
     * @return theIngredient
     */
    @Override
    public Ingredient getT(Ingredient ingredient) {
        Ingredient theIngredient = null;
        String ingredientNameEN = ingredient.getNameEN();
        String ingredientNameRU = ingredient.getNameRU();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select * from barbackdb.ingredients where ingredientNameEN=? and ingredientNameRU=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, ingredientNameEN);
            myStmt.setString(2, ingredientNameRU);
            myRs = myStmt.executeQuery();
            if (myRs.next()) {
                int ingredientID = myRs.getInt("ingredientID");
                boolean alcohol = myRs.getBoolean("isAlcohol");
                boolean action = myRs.getBoolean("isAction");
                theIngredient = new Ingredient(ingredientID, ingredientNameEN, ingredientNameRU, alcohol, action);
            } else {
                throw new IngredientDAOException(MessageManager.getProperty("message.couldnotfindingredient") + ingredientNameEN);
            }
            return theIngredient;
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } catch (IngredientDAOException e) {
            LOGGER.error(MessageManager.getProperty("message.couldnotfindingredient"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return theIngredient;
    }
    /**
     * To check ingredient by name if it is already in database barbackdb.ingredients.
     *
     * @param ingredientName
     * @return theIngredient
     */
    public Ingredient checkIngredient(String ingredientName) {
        Ingredient theIngredient = null;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select * from barbackdb.ingredients where ingredientNameEN=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, ingredientName);
            myRs = myStmt.executeQuery();
            if (myRs.next()) {
                int ingredientID = myRs.getInt(INGREDIENT_ID);
                String ingredientNameEN = myRs.getString(INGREDIENT_NAME_EN);
                String ingredientNameRU = myRs.getString(INGREDIENT_NAME_RU);
                boolean alcohol = myRs.getBoolean(IS_ALCOHOL);
                boolean action = myRs.getBoolean(IS_ACTION);
                theIngredient = new Ingredient(ingredientID, ingredientNameEN, ingredientNameRU, alcohol, action);
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return theIngredient;
    }
    /**
     * To get id of ingredient from database barbackdb.ingredients.
     *
     * @param ingredientName
     * @return id
     */
    public int getID(String ingredientName) {
        int id = 0;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select * from barbackdb.ingredients where ingredientNameEN=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, ingredientName);
            myRs = myStmt.executeQuery();
            if (myRs.next()) {
                id = myRs.getInt("ingredientID");
            } else {
                throw new IngredientDBException(MessageManager.getProperty("message.couldnotfindingredient") + ingredientName);
            }
        } catch (SQLException |  ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } catch (IngredientDBException e) {
            LOGGER.error(MessageManager.getProperty("message.couldnotfindingredient"));
        } finally {
            close(myConn, myStmt, myRs);
        }
        return id;
    }

    /**
     * To add ingredient to database barbackdb.ingredients.
     *
     * @param ingredientNameEN
     * @param ingredientNameRU
     */

    public void addIngredient(String ingredientNameEN, String ingredientNameRU){
        Connection myConn = null;
        PreparedStatement myStmt = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "insert into barbackdb.ingredients "
                    + "( ingredientNameEN, ingredientNameRU, isAlcohol, isAction)"
                    + "values ( ?, ?, ?, ? )";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, ingredientNameEN);
            myStmt.setString(2, ingredientNameRU);
            myStmt.setBoolean(3, false);
            myStmt.setBoolean(4, false);
            myStmt.execute();
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        } finally {
            close(myConn, myStmt, null);
        }
    }
    /**
     * To check if ingredient defined by name is already in database barbackdb.ingredients.
     *
     * @param ingredientNameEN
     * @return true if database already has ingredient with such name
     */
    public boolean checkIngredientByName(String ingredientNameEN) {
        boolean isregistered = false;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = ConnectionPool.POOL.getConnection();
            String sql = "select * from barbackdb.ingredients where ingredientNameEN=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, ingredientNameEN);
            myRs = myStmt.executeQuery();
            if (myRs.next()) {
                isregistered = true;
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error(MessageManager.getProperty("message.databaseerror"));
        }  finally {
            close(myConn, myStmt, myRs);
        }
        return isregistered;
    }
}