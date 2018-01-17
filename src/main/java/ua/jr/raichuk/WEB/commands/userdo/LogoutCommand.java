package ua.jr.raichuk.WEB.commands.userdo;

import ua.jr.raichuk.WEB.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jesus Raichuk
 */
public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("login");
        request.getSession().removeAttribute("name");
        request.getRequestDispatcher("login.jsp").forward(request, response);

    }
}
