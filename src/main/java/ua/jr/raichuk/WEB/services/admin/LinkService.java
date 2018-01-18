package ua.jr.raichuk.WEB.services.admin;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.DB.entities.impls.Norm;

/**
 * @author Jesus Raichuk
 */
public class LinkService extends AdminService<Link> {

    LinkService(){}

    @Override
    public Entity getEmptyClass() {
        return new Link();
    }
}
