package ua.jr.raichuk.WEB.commands.admindo.profile;

import ua.jr.raichuk.WEB.commands.admindo.AdminShowNewFormCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowNewProfileFormCommand extends AdminShowNewFormCommand {
    @Override
    protected String getRedirect() {
        return "admin/profileForm.jsp";
    }
}
