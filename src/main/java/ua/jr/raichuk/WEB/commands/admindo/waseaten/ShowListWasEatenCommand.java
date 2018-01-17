package ua.jr.raichuk.WEB.commands.admindo.waseaten;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.WasEaten;
import ua.jr.raichuk.WEB.commands.admindo.AdminShowListCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowListWasEatenCommand extends AdminShowListCommand<WasEaten> {
    @Override
    protected Entity getEntity() {
        return new WasEaten();
    }

    @Override
    protected String getRedirect() {
        return "admin/wasEatenList.jsp";
    }
}
