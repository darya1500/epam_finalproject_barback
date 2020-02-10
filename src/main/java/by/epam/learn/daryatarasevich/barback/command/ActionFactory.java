package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ActionFactory.class);
    private static final String ERROR_MESSAGE="wrongAction";
    private final static String COMMAND="command";
    /**
     * To define command from fixed list, specified in CommandEnum.
     * If command is empty or null it will be defined as EmptyCommand.
     *
     * @param request
     * @throws IllegalArgumentException
     * @return current
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(COMMAND);
        if (action == null || action.isEmpty()) {
            LOGGER.info(MessageManager.getProperty("message.actionisnull"));
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute(ERROR_MESSAGE, action + MessageManager.getProperty("message.wrongaction"));
            LOGGER.error(MessageManager.getProperty("message.listisnull"));
        }
        return current;
    }
}
