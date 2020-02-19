package by.epam.learn.daryatarasevich.barback.exception;

public class IncorrectPasswordException extends Exception {

    public IncorrectPasswordException(String message, Exception e) {
        super(message, e);
    }

    public IncorrectPasswordException() {

    }
}
