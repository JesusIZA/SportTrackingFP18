package ua.jr.raichuk.WEB.commands.admindo.link;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.WEB.commands.admindo.AdminShowEditFormCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowEditLinkFormCommand extends AdminShowEditFormCommand<Link> {
    @Override
    protected Entity getEntity() {
        return new Link();
    }

    @Override
    protected String getRedirect() {
        return "admin/linkForm.jsp";
    }
}
