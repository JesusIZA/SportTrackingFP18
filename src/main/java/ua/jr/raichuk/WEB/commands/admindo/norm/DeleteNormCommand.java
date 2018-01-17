package ua.jr.raichuk.WEB.commands.admindo.norm;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Norm;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminDeleteCommand;

/**
 * @author Jesus Raichuk
 */
public class DeleteNormCommand extends AdminDeleteCommand<Norm> {
    @Override
    protected Entity getEntity() {
        return new Norm();
    }

    @Override
    protected String getRedirect() {
        return FactoryCommand.ADMIN_SHOW_LIST_NORM;
    }
}