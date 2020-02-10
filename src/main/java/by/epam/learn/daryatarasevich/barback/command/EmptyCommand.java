package by.epam.learn.daryatarasevich.barback.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(EmptyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}