package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.exception.InvalidEmailException;
import by.epam.learn.daryatarasevich.barback.exception.InvalidPasswordException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.exception.RegistrationException;
import by.epam.learn.daryatarasevich.barback.logic.ListOfBartendersLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddBartenderCommand implements ActionCommand {
    private ListOfBartendersLogic listOfBartendersLogic = new ListOfBartendersLogic();
    private static final Logger LOGGER = LogManager.getLogger(AddBartenderCommand.class);

    /**
     * Command defines actions executed to add bartender to the barbackdb.users.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            listOfBartendersLogic.addBartender(request);
        } catch (NullPointerException e) {
            LOGGER.error(MessageManager.getProperty("message.addbartendererror"));
            request.getSession().setAttribute("whileAddingBartenderMessage", MessageManager.getProperty("message.addbartendererror"));
            page = ConfigurationManager.getProperty("path.page.addbartenderform");
            return page;
        } catch (InvalidEmailException e) {
            LOGGER.error(MessageManager.getProperty("message.emailisnotvalid"));
            request.getSession().setAttribute("whileAddingBartenderMessage", MessageManager.getProperty("message.emailisnotvalid"));
            page = ConfigurationManager.getProperty("path.page.addbartenderform");
            return page;
        } catch (InvalidPasswordException e) {
            LOGGER.error(MessageManager.getProperty("message.passwordistooshort"));
            request.getSession().setAttribute("whileAddingBartenderMessage", MessageManager.getProperty("message.passwordistooshort"));
            page = ConfigurationManager.getProperty("path.page.addbartenderform");
            return page;
        } catch (RegistrationException e) {
            LOGGER.error(MessageManager.getProperty("message.userisalreadyregistered"));
            request.getSession().setAttribute("whileAddingBartenderMessage", MessageManager.getProperty("message.userisalreadyregistered"));
            page = ConfigurationManager.getProperty("path.page.addbartenderform");
            return page;
        }
        page = ConfigurationManager.getProperty("path.page.main");
        request.getSession().setAttribute("whileAddingBartenderMessage", MessageManager.getProperty("message.successaddingbertender"));
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
