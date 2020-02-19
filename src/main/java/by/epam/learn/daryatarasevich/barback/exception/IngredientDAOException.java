package by.epam.learn.daryatarasevich.barback.exception;

public class IngredientDAOException extends Exception {


    public IngredientDAOException(String message, Exception e) {
        super(message, e);
    }

    public IngredientDAOException() {

    }

    public IngredientDAOException(String s) {
    }
}
