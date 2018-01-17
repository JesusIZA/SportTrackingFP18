package ua.jr.raichuk.WEB.commands.userdo;

import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.services.admin.AdminServiceFactory;
import ua.jr.raichuk.WEB.services.admin.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jesus Raichuk
 */
public class ShowProfileCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("login");
        try {
            ProfileService profileService = (ProfileService) AdminServiceFactory.getAdminService(new Profile());
            Profile profile = profileService.getProfile(login);
            request.setAttribute("name", profile.getName());
            request.setAttribute("surname", profile.getSurname());
            request.setAttribute("sex", profile.getSex());
            request.setAttribute("birthday", profile.getBirthday());
            request.setAttribute("height", profile.getHeight());
            request.setAttribute("weight", profile.getWeight());
            request.setAttribute("activeTime", profile.getActiveTime());

            request.getRequestDispatcher("editProfile.jsp").forward(request, response);
        } catch (TransactionException e) {
            request.setAttribute("error", "Profile error");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }
}
