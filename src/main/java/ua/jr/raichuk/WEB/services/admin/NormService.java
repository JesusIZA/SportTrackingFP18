package ua.jr.raichuk.WEB.services.admin;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Norm;

/**
 * @author Jesus Raichuk
 */
public class NormService extends AdminService<Norm> {

    NormService(){}

    @Override
    public Entity getEmptyClass() {
        return new Norm();
    }
}
