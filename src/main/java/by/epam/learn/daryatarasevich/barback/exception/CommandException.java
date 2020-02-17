package by.epam.learn.daryatarasevich.barback.exception;

public class CommandException extends Exception {
    private static final long serialVersionUID = 7903443055082238338L;

    public CommandException(String message, Exception e)
    {
        super(message, e);
    }
}