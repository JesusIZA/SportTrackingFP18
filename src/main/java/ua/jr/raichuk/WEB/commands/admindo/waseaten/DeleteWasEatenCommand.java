package ua.jr.raichuk.WEB.commands.admindo.waseaten;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.WasEaten;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminDeleteCommand;

/**
 * @author Jesus Raichuk
 */
public class DeleteWasEatenCommand extends AdminDeleteCommand<WasEaten> {
    @Override
    protected Entity getEntity() {
        return new WasEaten();
    }

    @Override
    protected String getRedirect() {
        return FactoryCommand.ADMIN_SHOW_LIST_WASEATEN;
    }
}