package by.epam.learn.daryatarasevich.barback.exception;

public class UnableDeleteAccountException extends Exception {
    private static final long serialVersionUID = 1L;

    public UnableDeleteAccountException(String message, Exception e) {
        super(message, e);
    }


}
