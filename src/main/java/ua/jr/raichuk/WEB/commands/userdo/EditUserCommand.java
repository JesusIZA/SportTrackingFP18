package ua.jr.raichuk.WEB.commands.userdo;

import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.services.admin.AdminServiceFactory;
import ua.jr.raichuk.WEB.services.user.LoginService;
import ua.jr.raichuk.WEB.services.admin.UserService;
import ua.jr.raichuk.WEB.validators.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Jesus Raichuk
 */
public class EditUserCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String login = (String) request.getSession().getAttribute("login");

            String newLogin = request.getParameter("newLogin");
            String newPassword = request.getParameter("newPassword");
            String oldPassword = request.getParameter("oldPassword");

            if (Objects.isNull(newLogin) || Objects.isNull(newPassword) || Objects.isNull(oldPassword)) {
                request.setAttribute("error", "All fields must been not null");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            LoginService loginService = LoginService.getService();
            try {
                if (loginService.verify(login, oldPassword) && EnterDataValidator.isValidLogin(newLogin) &&
                        EnterDataValidator.isFreeLogin(newLogin) &&
                        EnterDataValidator.isValidPassword(newPassword)) {
                    UserService editUserService = (UserService) AdminServiceFactory.getAdminService(new User());
                    editUserService.edit(login, newLogin, newPassword);
                    request.getSession().setAttribute("login", newLogin);
                    FactoryCommand.getInstance().getCommand(FactoryCommand.SHOW_PROFILE).execute(request, response);
                    return;
                } else
                    throw new DataException("Passwords or login is incorrect");
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
