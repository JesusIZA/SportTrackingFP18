package ua.jr.raichuk.WEB.commands.admindo.link;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminDeleteCommand;

/**
 * @author Jesus Raichuk
 */
public class DeleteLinkCommand extends AdminDeleteCommand<Link> {
    @Override
    protected Entity getEntity() {
        return new Link();
    }

    @Override
    protected String getRedirect() {
        return FactoryCommand.ADMIN_SHOW_LIST_LINK;
    }
}