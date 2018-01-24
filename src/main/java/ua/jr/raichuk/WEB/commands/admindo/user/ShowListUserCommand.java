package ua.jr.raichuk.WEB.commands.admindo.user;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.WEB.commands.admindo.AdminShowListCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowListUserCommand extends AdminShowListCommand<User> {
    @Override
    protected Entity getEntity() {
        return new User();
    }

    @Override
    protected String getRedirect() {
        return "admin/userList.jsp";
    }
}
