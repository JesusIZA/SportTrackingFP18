package ua.jr.raichuk.WEB.commands.admindo.food;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.WEB.commands.admindo.AdminShowEditFormCommand;

/**
 * @author Jesus Raichuk
 */
public class ShowEditFoodFormCommand extends AdminShowEditFormCommand<Food> {
    @Override
    protected Entity getEntity() {
        return new Food();
    }

    @Override
    protected String getRedirect() {
        return "admin/foodForm.jsp";
    }
}
