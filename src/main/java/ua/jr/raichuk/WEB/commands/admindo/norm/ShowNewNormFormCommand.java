package ua.jr.raichuk.WEB.commands.admindo.norm;

import ua.jr.raichuk.WEB.commands.admindo.AdminShowNewFormCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowNewNormFormCommand extends AdminShowNewFormCommand {
    @Override
    protected String getRedirect() {
        return "admin/normForm.jsp";
    }
}
