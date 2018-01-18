package ua.jr.raichuk.WEB.commands.admindo.profile;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.WEB.commands.admindo.AdminShowEditFormCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowEditProfileFormCommand extends AdminShowEditFormCommand<Profile> {
    @Override
    protected Entity getEntity() {
        return new Profile();
    }

    @Override
    protected String getRedirect() {
        return "admin/profileForm.jsp";
    }
}
