package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.exception.InvalidEmailException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.logic.HelpLogic;
import by.epam.learn.daryatarasevich.barback.validation.HelpValidator;
import by.epam.learn.daryatarasevich.barback.validation.RegisterValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class HelpCommand implements ActionCommand {
    HelpLogic helpLogic = new HelpLogic();
    private static final Logger LOGGER = LogManager.getLogger(HelpCommand.class);
    HelpValidator helpValidator = new HelpValidator();
    RegisterValidator registerValidator = new RegisterValidator();

    /**
     * Command defines actions executed to add users message asking for help to database barbackdb.help.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");
        boolean validated = helpValidator.validate(email, name, message);
        if (validated) {
            boolean validatedEmail = false;
            try {
                validatedEmail = registerValidator.validateEmail(email);
            } catch (InvalidEmailException e) {
                request.setAttribute("message", MessageManager.getProperty("message.informationnotvalid"));
                LOGGER.error(MessageManager.getProperty("message.informationnotvalid"));
                page = ConfigurationManager.getProperty("path.page.main");
                return page;
            }
            if (validatedEmail) {
                helpLogic.send(name, email, message);
                LOGGER.info(MessageManager.getProperty("message.messagesent"));
                request.setAttribute("defaultMessage", MessageManager.getProperty("message.messagesent"));
                page = ConfigurationManager.getProperty("path.page.main");
            }
        } else {
            request.setAttribute("message", MessageManager.getProperty("message.informationnotvalid"));
            LOGGER.info(MessageManager.getProperty("message.informationnotvalid"));
            page = ConfigurationManager.getProperty("path.page.main");
            return page;
        }
        return page;
    }
}
