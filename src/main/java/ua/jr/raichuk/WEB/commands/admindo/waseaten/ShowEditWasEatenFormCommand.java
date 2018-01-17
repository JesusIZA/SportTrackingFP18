package ua.jr.raichuk.WEB.commands.admindo.waseaten;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.WasEaten;
import ua.jr.raichuk.WEB.commands.admindo.AdminShowEditFormCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowEditWasEatenFormCommand extends AdminShowEditFormCommand<WasEaten> {
    @Override
    protected Entity getEntity() {
        return new WasEaten();
    }

    @Override
    protected String getRedirect() {
        return "admin/wasEatenForm.jsp";
    }
}
