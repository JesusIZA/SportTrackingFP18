package ua.jr.raichuk.WEB.commands.userdo;

import org.apache.log4j.Logger;
import ua.jr.raichuk.WEB.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jesus Raichuk
 */
public class LogoutCommand implements Command {
    private static Logger LOGGER = Logger.getLogger(LogoutCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Command.User (LogoutCommand.execute()) info : User " + request.getSession().getAttribute("login") + " sign out of site");
        request.getSession().removeAttribute("login");
        request.getSession().removeAttribute("name");
        request.getRequestDispatcher("login.jsp").forward(request, response);

    }
}
