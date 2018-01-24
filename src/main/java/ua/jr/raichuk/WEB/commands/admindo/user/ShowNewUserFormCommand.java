package ua.jr.raichuk.WEB.commands.admindo.user;

import ua.jr.raichuk.WEB.commands.admindo.AdminShowNewFormCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowNewUserFormCommand extends AdminShowNewFormCommand {
    @Override
    protected String getRedirect() {
        return "admin/userForm.jsp";
    }
}
