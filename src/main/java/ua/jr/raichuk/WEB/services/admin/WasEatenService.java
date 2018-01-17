package ua.jr.raichuk.WEB.services.admin;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.WasEaten;

/**
 * @author Jesus Raichuk
 */
public class WasEatenService extends AdminService<WasEaten>{
    WasEatenService() {};

    @Override
    public Entity getEmptyClass() {
        return new WasEaten();
    }
}
