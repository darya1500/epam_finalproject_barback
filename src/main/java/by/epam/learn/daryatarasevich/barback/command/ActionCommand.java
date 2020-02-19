package by.epam.learn.daryatarasevich.barback.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute(HttpServletRequest request);

    /**
     * Boolean marks commands that require redirect instead of forward in ControllerServlet.
     *
     * @return true if page requires redirect
     */
    default boolean requiresRedirect() {
        return false;
    }
}
