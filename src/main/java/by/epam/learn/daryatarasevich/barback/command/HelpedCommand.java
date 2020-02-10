package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entities.Help;
import by.epam.learn.daryatarasevich.barback.exception.MessageConstants;
import by.epam.learn.daryatarasevich.barback.logic.HelpLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HelpedCommand implements ActionCommand {
    HelpLogic helpLogic;
    private static final Logger LOGGER = LogManager.getLogger(HelpedCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        helpLogic=new HelpLogic();
        helpLogic.update(request);
        List<Help> list=helpLogic.getAll();
        request.setAttribute("THE_LIST",list);
        page = ConfigurationManager.getProperty("path.page.whoneedshelp");
        return page;
    }
}