package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.logic.ClientType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(LogoutCommand.class);
    /**
     * To logout user.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.main");
        request.getSession().invalidate();
        request.getSession().setAttribute("role", ClientType.GUEST);
        LOGGER.info(MessageManager.getProperty("message.successfullogout"));
        return page;
    }
}
