package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entities.Cocktail;
import by.epam.learn.daryatarasevich.barback.entities.User;
import by.epam.learn.daryatarasevich.barback.exception.IncorrectPasswordException;
import by.epam.learn.daryatarasevich.barback.exception.NoSuchUserException;
import by.epam.learn.daryatarasevich.barback.logic.ClientType;
import by.epam.learn.daryatarasevich.barback.logic.DeleteAccountLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteAccountCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteAccountCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws NoSuchUserException, IncorrectPasswordException, NamingException {
        String page = null;
        User user= (User) request.getSession().getAttribute("logedUser");
        DeleteAccountLogic deleteAccountLogic=new DeleteAccountLogic();
        String message=deleteAccountLogic.delete(user);
        request.setAttribute ("errorMessage", message);
        request.getSession().setAttribute("role", ClientType.GUEST);
        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
