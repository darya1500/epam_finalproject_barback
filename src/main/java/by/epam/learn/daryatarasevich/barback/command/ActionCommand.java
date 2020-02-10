package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.exception.IncorrectPasswordException;
import by.epam.learn.daryatarasevich.barback.exception.IngredientDBException;
import by.epam.learn.daryatarasevich.barback.exception.NoSuchUserException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute(HttpServletRequest request) throws NoSuchUserException, IncorrectPasswordException, NamingException, IngredientDBException;
}
