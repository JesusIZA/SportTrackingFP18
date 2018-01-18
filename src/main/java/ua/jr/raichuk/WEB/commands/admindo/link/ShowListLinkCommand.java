package ua.jr.raichuk.WEB.commands.admindo.link;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.WEB.commands.admindo.AdminShowListCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowListLinkCommand extends AdminShowListCommand<Link> {
    @Override
    protected Entity getEntity() {
        return new Link();
    }

    @Override
    protected String getRedirect() {
        return "admin/linkList.jsp";
    }
}
