package by.epam.learn.daryatarasevich.barback.controller;

import by.epam.learn.daryatarasevich.barback.command.ActionCommand;
import by.epam.learn.daryatarasevich.barback.command.ActionFactory;
import by.epam.learn.daryatarasevich.barback.command.ConfigurationManager;
import by.epam.learn.daryatarasevich.barback.exception.*;
import by.epam.learn.daryatarasevich.barback.pool.ConnectionPool;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/controller")
public class Controller extends HttpServlet {


    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionPool pool = ConnectionPool.POOL;
        if (!pool.isCreated().get()) {
            try {
                pool.initPoolData();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).setAttribute("local", request.getParameter("local"));
        response.sendRedirect(request.getHeader("referer"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(true).setAttribute("local", request.getParameter("local"));
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        try {
            page = command.execute(request);
        } catch (NoSuchUserException | IncorrectPasswordException | NamingException | IngredientDBException e) {
            e.printStackTrace();
        }
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            try {
                response.sendRedirect(request.getContextPath() + page);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
