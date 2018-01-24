package ua.jr.raichuk.WEB.commands.userdo;

import org.apache.log4j.Logger;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.authentication.Authentication;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.services.user.LoginService;
import ua.jr.raichuk.WEB.validators.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command check entered login and password and open:
 * Error page if data is incorrect
 * Tracking page if it is user and data is correct
 * Admin user page if it is admin
 *
 * @author Jesus Raichuk
 */
public class LoginCommand implements Command {
    private static Logger LOGGER = Logger.getLogger(LoginCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String login = request.getParameter("login");

            String password = request.getParameter("password");
            try {
                if (!EnterDataValidator.isValidLogin(login)) {
                    throw new DataException("Login is incorrect");
                } else if (!EnterDataValidator.isValidPassword(password)) {
                    throw new DataException("Password is incorrect");
                } else {
                    LoginService loginService = LoginService.getService();
                    if (loginService.verify(login, password)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("login", login);
                        if(Authentication.isAdmin(session)) {
                            LOGGER.info("Command.User (LoginCommand.execute()) info : User " + login + " sign into site");
                            FactoryCommand.getInstance().getCommand(FactoryCommand.ADMIN_SHOW_LIST_USER).execute(request, response);
                        } else {
                            FactoryCommand.getInstance().getCommand(FactoryCommand.TRACKING).execute(request, response);
                        }
                    } else {
                        request.setAttribute("error", "You are not registered");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                }
            } catch (DataException e) {
                LOGGER.debug("Command.Admin->Data (LoginCommand.execute()) exception : Data is incorrect!");
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (TransactionException e1) {
                LOGGER.error("DB.DAO(CRUD)->Command.User (LoginCommand.execute()) exception : read error!");
                request.setAttribute("error", "Server error");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e2) {
            LOGGER.debug("Command.User->Data (LoginCommand.execute()) exception : Data is illegal!");
            request.setAttribute("error", "Data is illegal");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
