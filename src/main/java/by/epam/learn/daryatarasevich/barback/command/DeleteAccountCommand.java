package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.User;
import by.epam.learn.daryatarasevich.barback.logic.ClientType;
import by.epam.learn.daryatarasevich.barback.logic.DeleteAccountLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteAccountCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteAccountCommand.class);

    /**
     * Command defines actions executed to delete user account by deleting user from barbackdb.users.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        User user = (User) request.getSession().getAttribute("logedUser");
        DeleteAccountLogic deleteAccountLogic = new DeleteAccountLogic();
        String message = deleteAccountLogic.delete(user);
        request.setAttribute("errorMessage", message);
        request.removeAttribute("role");
        request.removeAttribute("USER");
        request.getSession().setAttribute("role", ClientType.GUEST);
        page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}
