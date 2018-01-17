package ua.jr.raichuk.WEB.commands.admindo.norm;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Norm;
import ua.jr.raichuk.WEB.commands.admindo.AdminShowEditFormCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowEditNormFormCommand extends AdminShowEditFormCommand<Norm> {
    @Override
    protected Entity getEntity() {
        return new Norm();
    }

    @Override
    protected String getRedirect() {
        return "admin/normForm.jsp";
    }
}
