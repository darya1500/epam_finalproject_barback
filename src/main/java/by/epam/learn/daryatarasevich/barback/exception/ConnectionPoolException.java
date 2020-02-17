package by.epam.learn.daryatarasevich.barback.exception;

public class ConnectionPoolException extends Exception {

    private static final long serialVersionUID = -302653759317412082L;

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
