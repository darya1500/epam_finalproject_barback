package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.dao.UserDAO;
import by.epam.learn.daryatarasevich.barback.entities.User;
import by.epam.learn.daryatarasevich.barback.exception.MessageConstants;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import by.epam.learn.daryatarasevich.barback.logic.HelpLogic;
import by.epam.learn.daryatarasevich.barback.validation.HelpValidation;
import by.epam.learn.daryatarasevich.barback.validation.RegisterValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class HelpCommand implements ActionCommand {
    HelpLogic helpLogic=new HelpLogic();
    private static final Logger LOGGER = LogManager.getLogger(HelpCommand.class);
    HelpValidation helpValidation=new HelpValidation();
    RegisterValidation registerValidation=new RegisterValidation();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String name = request.getParameter ("name");
        String email = request.getParameter ("email");
        String message = request.getParameter ("message");
        boolean validated=helpValidation.validate(email,name,message);
        if (validated){
            boolean validatedEmail=registerValidation.validateEmail(email);
            if (validatedEmail){
                helpLogic.send(name,email,message);
                request.setAttribute ("defaultMessage", MessageManager.getProperty("message.messagesent"));
                page = ConfigurationManager.getProperty("path.page.main");
            }else {
                request.setAttribute ("message", MessageManager.getProperty("message.informationnotvalid"));
                page = ConfigurationManager.getProperty("path.page.main");
                return page;
            }
        }else {
            request.setAttribute ("message", MessageManager.getProperty("message.informationnotvalid"));
            page = ConfigurationManager.getProperty("path.page.main");
            return page;
        }
        return page;
    }
}
