package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.Help;
import by.epam.learn.daryatarasevich.barback.logic.HelpLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HelpedCommand implements ActionCommand {
    HelpLogic helpLogic;
    private static final Logger LOGGER = LogManager.getLogger(HelpedCommand.class);

    /**
     * Command defines actions executed to change status of messages asking for help from users from PENDING to HELPED.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        helpLogic = new HelpLogic();
        helpLogic.update(request);
        List<Help> list = helpLogic.getAll();
        request.setAttribute("THE_LIST", list);
        page = ConfigurationManager.getProperty("path.page.whoneedshelp");
        return page;
    }
}