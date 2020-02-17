package by.epam.learn.daryatarasevich.barback.command;

import by.epam.learn.daryatarasevich.barback.entity.Status;
import by.epam.learn.daryatarasevich.barback.entity.User;
import by.epam.learn.daryatarasevich.barback.logic.ListOfUsersLogic;
import javax.servlet.http.HttpServletRequest;

public class ChangeStatusCommand implements ActionCommand {
    ListOfUsersLogic listOfUsersLogic=new ListOfUsersLogic();

    @Override
    public String execute(HttpServletRequest request) {
        String page=null;
        int userID= Integer.parseInt(request.getParameter("userID"));
        User user=listOfUsersLogic.loadUser(request);
        Status status=user.getStatus();
        String newStatus=null;
        if (status.equals(Status.USER)){
            newStatus="BARTENDER";
        }
        if (status.equals(Status.BARTENDER)){
            newStatus="USER";
        }
        if (status!=null) {
            listOfUsersLogic.changestatus(userID, newStatus);
            request.setAttribute("message","Status was changed successfully");
        }else{
            request.setAttribute("message","Error during changing status");
        }
        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
