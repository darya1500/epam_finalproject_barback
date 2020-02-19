package by.epam.learn.daryatarasevich.barback.exception;

public class NoSuchUserException extends Exception {


    public NoSuchUserException(String message, Exception e) {
        super(message, e);
    }

    public NoSuchUserException() {

    }

    public NoSuchUserException(String s) {
    }
}
