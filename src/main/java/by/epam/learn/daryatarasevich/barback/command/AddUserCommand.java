package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.exception.InvalidEmailException;
import by.epam.learn.daryatarasevich.barback.exception.InvalidPasswordException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.exception.RegistrationException;
import by.epam.learn.daryatarasevich.barback.logic.ListOfUsersLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddUserCommand implements ActionCommand {
    private ListOfUsersLogic listOfUsersLogic = new ListOfUsersLogic();
    private static final Logger LOGGER = LogManager.getLogger(AddUserCommand.class);

    /**
     * Command defines actions executed to add user to the barbackdb.users.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            listOfUsersLogic.addUser(request);
        } catch (NullPointerException e) {
            LOGGER.error(MessageManager.getProperty("message.addusererror"));
            request.getSession().setAttribute("whileAddingUserMessage", MessageManager.getProperty("message.addusererror"));
            page = ConfigurationManager.getProperty("path.page.adduserform");
            return page;
        } catch (InvalidEmailException e) {
            LOGGER.error(MessageManager.getProperty("message.emailisnotvalid"));
            request.getSession().setAttribute("whileAddingUserMessage", MessageManager.getProperty("message.emailisnotvalid"));
            page = ConfigurationManager.getProperty("path.page.adduserform");
            return page;
        } catch (InvalidPasswordException e) {
            LOGGER.error(MessageManager.getProperty("message.passwordistooshort"));
            request.getSession().setAttribute("whileAddingUserMessage", MessageManager.getProperty("message.passwordistooshort"));
            page = ConfigurationManager.getProperty("path.page.adduserform");
            return page;
        } catch (RegistrationException e) {
            LOGGER.error(MessageManager.getProperty("message.userisalreadyregistered"));
            request.getSession().setAttribute("whileAddingUserMessage", MessageManager.getProperty("message.userisalreadyregistered"));
            page = ConfigurationManager.getProperty("path.page.adduserform");
            return page;
        }
        page = ConfigurationManager.getProperty("path.page.main");
        request.getSession().setAttribute("whileAddingUserMessage", MessageManager.getProperty("message.successaddinguser"));
        return page;
    }

    /**
     * Boolean marks commands that require redirect instead of forward in ControllerServlet.
     *
     * @return true if page requires redirect
     */
    @Override
    public boolean requiresRedirect() {
        return true;
    }
}
