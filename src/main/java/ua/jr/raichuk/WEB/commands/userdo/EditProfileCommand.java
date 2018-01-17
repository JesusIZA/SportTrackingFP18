package ua.jr.raichuk.WEB.commands.userdo;

import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.services.admin.AdminServiceFactory;
import ua.jr.raichuk.WEB.services.admin.ProfileService;
import ua.jr.raichuk.WEB.validators.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Objects;

/**
 * @author Jesus Raichuk
 */
public class EditProfileCommand implements Command {
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
                if (!EnterDataValidator.isValidNameOrSurname(name)) {
                    throw new DataException("Name is incorrect");
                } else if (!EnterDataValidator.isValidNameOrSurname(surname)) {
                    throw new DataException("Surname is incorrect");
                }  else if (!EnterDataValidator.isValidSex(sex)) {
                    throw new DataException("Sex is incorrect");
                } else if (!EnterDataValidator.isValidDate(birthday)) {
                    throw new DataException("Birthday is incorrect");
                } else if (!EnterDataValidator.isValidHeightOrWeight(height)) {
                    throw new DataException("Height is incorrect");
                } else if (!EnterDataValidator.isValidHeightOrWeight(weight)) {
                    throw new DataException("Weight is incorrect");
                } else if (!EnterDataValidator.isValidActiveTime(activeTime)) {
                    throw new DataException("Active time is incorrect");
                } else {

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
