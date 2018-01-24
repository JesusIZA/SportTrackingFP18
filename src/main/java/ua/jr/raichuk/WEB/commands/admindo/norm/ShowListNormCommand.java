package ua.jr.raichuk.WEB.commands.admindo.norm;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Norm;
import ua.jr.raichuk.WEB.commands.admindo.AdminShowListCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowListNormCommand extends AdminShowListCommand<Norm> {
    @Override
    protected Entity getEntity() {
        return new Norm();
    }

    @Override
    protected String getRedirect() {
        return "admin/normList.jsp";
    }
}
