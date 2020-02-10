package by.epam.learn.daryatarasevich.barback.exception;

import java.beans.ExceptionListener;

public class IngredientDBException extends Exception {
    private static final long serialVersionUID = 1L;

    public IngredientDBException(String message, Exception e) {
        super(message, e);
    }

    public IngredientDBException(String message) {
        super(message);
    }

    public IngredientDBException() {
    }
}
