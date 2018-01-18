package ua.jr.raichuk.WEB.commands.userdo;

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

public class LoginCommand implements Command {
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
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (TransactionException e1) {
                request.setAttribute("error", "Server error");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e2) {
            request.setAttribute("error", "Data is illegal");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
