package ua.jr.raichuk.WEB.commands.userdo;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.services.admin.AdminServiceFactory;
import ua.jr.raichuk.WEB.services.admin.ProfileService;
import ua.jr.raichuk.WEB.validators.ProfileValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Objects;

/**
 * Command change profile data if it is valid
 *
 * @author Jesus Raichuk
 */
public class EditProfileCommand implements Command {

    private static Logger LOGGER = Logger.getLogger(EditProfileCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String login = (String) request.getSession().getAttribute("login");

            String name = (String) request.getParameter("name");
            String surname = (String) request.getParameter("surname");
            String sex = (String)(request.getParameter("sex"));
            Date birthday = Date.valueOf(request.getParameter("birthday"));
            Double height = Double.parseDouble(request.getParameter("height"));
            Double weight = Double.parseDouble(request.getParameter("weight"));
            Integer activeTime = Integer.parseInt(request.getParameter("activeTime"));

            System.out.println(birthday);

            if (name.equals("") || surname.equals("") || Objects.isNull(birthday) || Objects.isNull(height)
                    || Objects.isNull(weight) || Objects.isNull(activeTime)) {
                request.setAttribute("error", "All fields must been not null");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            try {
                if (ProfileValidator.isValid(name, surname, sex, birthday, height, weight, activeTime)){

                    Profile profile = new Profile();
                    profile.setName(name);
                    profile.setSurname(surname);
                    profile.setSex(sex);
                    profile.setBirthday(birthday);
                    profile.setHeight(height);
                    profile.setWeight(weight);
                    profile.setActiveTime(activeTime);

                    System.out.println(profile);

                    ProfileService profileService = (ProfileService) AdminServiceFactory.getAdminService(profile);
                    profileService.editProfile(login, profile);
                    request.getSession().removeAttribute("name");

                    FactoryCommand.getInstance().getCommand(FactoryCommand.TRACKING).execute(request, response);
                } else {
                    throw new DataException("Dats is incorrect");
                }
            } catch (DataException e) {
                LOGGER.debug("Command.Admin->Data (EditProfileCommand.execute()) exception : Data is incorrect!");
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (TransactionException e1) {
                LOGGER.error("DB.DAO(CRUD)->Command.User (EditProfileCommand.execute()) exception : edit error!");
                request.setAttribute("error", "Server error");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e2) {
            LOGGER.debug("Command.User->Data (EditProfileCommand.execute()) exception : Data is illegal!");
            request.setAttribute("error", "Data is illegal");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
