package ua.jr.raichuk.WEB.commands.userdo;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.services.user.RegistrationService;
import ua.jr.raichuk.WEB.validators.ProfileValidator;
import ua.jr.raichuk.WEB.validators.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.Objects;

/**
 * Command register new profile:
 * Add new user item
 * Add new profile item
 * Add new norm item
 *
 * @author Jesus Raichuk
 */
public class RegistrationCommand implements Command {
    private static Logger LOGGER = Logger.getLogger(RegistrationCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String login = request.getParameter("login");

            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String sex = (String)request.getParameter("sex");
            Date birthday = Date.valueOf(request.getParameter("birthday"));
            Double height = Double.parseDouble(request.getParameter("height"));
            Double weight = Double.parseDouble(request.getParameter("weight"));
            Integer activeTime = Integer.parseInt(request.getParameter("activeTime"));

            if (login.equals("") || password.equals("") || name.equals("") || surname.equals("")
                    || Objects.isNull(birthday) || Objects.isNull(height) || Objects.isNull(weight)
                    || Objects.isNull(activeTime)) {
                request.setAttribute("error", "All fields must been not null");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }
            try {
                 if (UserValidator.isValid(login, password) &&
                         ProfileValidator.isValid(name, surname, sex, birthday, height, weight, activeTime)){
                    User user = new User(login, password);
                    Profile profile = new Profile(name, surname, sex, birthday, height, weight, activeTime);

                    RegistrationService registerService = RegistrationService.getService();

                    registerService.register(user, profile);
                    HttpSession session = request.getSession();
                    session.setAttribute("login", login);
                    FactoryCommand.getInstance().getCommand(FactoryCommand.TRACKING).execute(request, response);
                } else {
                     throw new DataException("Data is incorrect");
                 }
            } catch (DataException e) {
                LOGGER.debug("Command.Admin->Data (RegistrationCommand.execute()) exception : Data is incorrect!");
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (TransactionException e1) {
                LOGGER.error("DB.DAO(CRUD)->Command.User (RegistrationCommand.execute()) exception : insert error!");
                request.setAttribute("error", "Server error");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e2) {
            LOGGER.debug("Command.User->Data (RegistrationCommand.execute()) exception : Data is illegal!");
            request.setAttribute("error", "Data is illegal");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
