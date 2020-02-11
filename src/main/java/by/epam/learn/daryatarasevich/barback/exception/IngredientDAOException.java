package by.epam.learn.daryatarasevich.barback.exception;

public class IngredientDAOException extends Exception {
    private static final long serialVersionUID = 1L;


    public IngredientDAOException(String message, Exception e) {
        super(message, e);
    }

    public IngredientDAOException() {

    }

    public IngredientDAOException(String s) {
    }
}
