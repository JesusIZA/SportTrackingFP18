package ua.jr.raichuk.WEB.commands.admindo.food;

import ua.jr.raichuk.WEB.commands.admindo.AdminShowNewFormCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowNewFoodFormCommand extends AdminShowNewFormCommand {
    @Override
    protected String getRedirect() {
        return "admin/foodForm.jsp";
    }
}
