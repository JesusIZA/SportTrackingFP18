package ua.jr.raichuk.WEB.commands.admindo.user;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminDeleteCommand;

/**
 * @author Jesus Raichuk
 */
public class DeleteUserCommand extends AdminDeleteCommand<User> {
    @Override
    protected Entity getEntity() {
        return new User();
    }

    @Override
    protected String getRedirect() {
        return FactoryCommand.ADMIN_SHOW_LIST_USER;
    }
}