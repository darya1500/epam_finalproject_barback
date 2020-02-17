package by.epam.learn.daryatarasevich.barback.exception;

public class IncorrectPasswordException extends Exception {
    private static final long serialVersionUID = -4309529890715301947L;


    public IncorrectPasswordException(String message, Exception e) {
        super(message, e);
    }

    public IncorrectPasswordException() {

    }
}
