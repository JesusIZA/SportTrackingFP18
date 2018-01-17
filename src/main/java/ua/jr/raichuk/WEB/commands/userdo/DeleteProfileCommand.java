package ua.jr.raichuk.WEB.commands.userdo;

import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.services.admin.AdminServiceFactory;
import ua.jr.raichuk.WEB.services.user.LoginService;
import ua.jr.raichuk.WEB.services.admin.ProfileService;
import ua.jr.raichuk.WEB.validators.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jesus Raichuk
 */
public class DeleteProfileCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String login = (String) request.getSession().getAttribute("login");

            String passwordE = request.getParameter("password");
            String loginE = request.getParameter("login");
            try {
                LoginService loginService = LoginService.getService();
                if (!EnterDataValidator.isValidLogin(loginE) || !EnterDataValidator.isValidPassword(passwordE)
                        || !loginService.verify(login, passwordE)) {
                    throw new DataException("Login or password is wrong");
                } else {
                    ProfileService profileService = (ProfileService) AdminServiceFactory.getAdminService(new Profile());
                    try {
                        profileService.deleteProfile(login);
                        request.getSession().removeAttribute("login");
                        request.getSession().removeAttribute("name");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                        return;
                    } catch (Exception e) {
                        request.setAttribute("error", "I am sorry you cannot delete your profile now. Try to do it late");
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
