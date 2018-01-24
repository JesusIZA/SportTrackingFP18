package ua.jr.raichuk.WEB.commands.admindo.profile;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminDeleteCommand;

/**
 * @author Jesus Raichuk
 */
public class DeleteProfileCommand extends AdminDeleteCommand<Profile> {
    @Override
    protected Entity getEntity() {
        return new Profile();
    }

    @Override
    protected String getRedirect() {
        return FactoryCommand.ADMIN_SHOW_LIST_PROFILE;
    }
}