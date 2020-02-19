package by.epam.learn.daryatarasevich.barback.controller;

import by.epam.learn.daryatarasevich.barback.command.ActionCommand;
import by.epam.learn.daryatarasevich.barback.command.ActionFactory;
import by.epam.learn.daryatarasevich.barback.command.ConfigurationManager;
import by.epam.learn.daryatarasevich.barback.exception.*;
import by.epam.learn.daryatarasevich.barback.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(ControllerServlet.class);

    /**
     * To init ControllerServlet.
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionPool pool = ConnectionPool.POOL;
        if (!pool.isCreated().get()) {
            try {
                pool.initPoolData();
            } catch (ConnectionPoolException e) {
                LOGGER.error(MessageManager.getProperty("message.connectionpoolexception"));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getHeader("referer"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    /**
     * To process request. At first to get command from request. Then to execute command.
     * And to either redirect to defined page if command requires redirect or to forward if command does not require redirect.
     *
     * @param request
     * @param response
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            try {
                if (command.requiresRedirect()) {
                    response.sendRedirect(request.getContextPath() + page);
                } else {
                    dispatcher.forward(request, response);
                }
            } catch (ServletException e) {
                LOGGER.error(MessageManager.getProperty("message.servletexception"));
            } catch (IOException e) {
                LOGGER.error(MessageManager.getProperty("message.ioexception"));
            }
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            try {
                response.sendRedirect(request.getContextPath() + page);
            } catch (IOException e) {
                LOGGER.error(MessageManager.getProperty("message.ioexception"));
            }
        }
    }
}
