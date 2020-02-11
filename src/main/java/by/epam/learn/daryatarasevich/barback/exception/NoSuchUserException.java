package by.epam.learn.daryatarasevich.barback.exception;

public class NoSuchUserException extends Exception {

    private static final long serialVersionUID = 1L;

    public NoSuchUserException(String message, Exception e) {
        super(message, e);
    }

    public NoSuchUserException() {

    }

    public NoSuchUserException(String s) {
    }
}
