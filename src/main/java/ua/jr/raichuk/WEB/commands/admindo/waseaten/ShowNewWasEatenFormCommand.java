package ua.jr.raichuk.WEB.commands.admindo.waseaten;

import ua.jr.raichuk.WEB.commands.admindo.AdminShowNewFormCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowNewWasEatenFormCommand extends AdminShowNewFormCommand {
    @Override
    protected String getRedirect() {
        return "admin/wasEatenForm.jsp";
    }
}
