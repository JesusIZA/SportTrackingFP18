package ua.jr.raichuk.WEB.commands.admindo.link;

import ua.jr.raichuk.WEB.commands.admindo.AdminShowNewFormCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowNewLinkFormCommand extends AdminShowNewFormCommand {
    @Override
    protected String getRedirect() {
        return "admin/linkForm.jsp";
    }
}
