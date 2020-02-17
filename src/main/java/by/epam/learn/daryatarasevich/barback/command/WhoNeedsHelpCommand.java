package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.Help;
import by.epam.learn.daryatarasevich.barback.logic.HelpLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class WhoNeedsHelpCommand implements ActionCommand {
   HelpLogic helpLogic=new HelpLogic();
    private static final Logger LOGGER = LogManager.getLogger(WhoNeedsHelpCommand.class);
    /**
     * To get all information from database about users who needs help.
     *
     * @param request
     * @return page
     */
    @Override
    public String execute(HttpServletRequest request)  {
        String page = null;
        List<Help> list=helpLogic.getAll();
        request.setAttribute("THE_LIST",list);
        page = ConfigurationManager.getProperty("path.page.whoneedshelp");
        return page;
    }
}