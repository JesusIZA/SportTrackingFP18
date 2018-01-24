package ua.jr.raichuk.WEB.commands.admindo.user;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminInsertCommand;
import ua.jr.raichuk.WEB.validators.UserValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jesus Raichuk
 */
public class InsertUserCommand extends AdminInsertCommand<User> {
    @Override
    protected Entity getEntity() {
        return new User();
    }

    @Override
    protected String getRedirect() {
        return FactoryCommand.ADMIN_SHOW_LIST_USER;
    }

    @Override
    protected User getValidEntity(HttpServletRequest request) throws DataException {
        String login = request.getParameter("loginU");
        String password = request.getParameter("passwordU");

        if (UserValidator.isValid(login, password)) {
            User user = new User(login, password);
            return user;
        } else {
            throw new DataException("Data is incorrect");
        }
    }
}
