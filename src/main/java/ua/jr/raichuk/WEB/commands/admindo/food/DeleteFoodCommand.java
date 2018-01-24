package ua.jr.raichuk.WEB.commands.admindo.food;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminDeleteCommand;
import ua.jr.raichuk.WEB.services.admin.AdminService;
import ua.jr.raichuk.WEB.services.admin.AdminServiceFactory;
import ua.jr.raichuk.WEB.services.admin.FoodService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jesus Raichuk
 */
public class DeleteFoodCommand extends AdminDeleteCommand<Food> {
    @Override
    protected Entity getEntity() {
        return new Food();
    }

    @Override
    protected String getRedirect() {
        return FactoryCommand.ADMIN_SHOW_LIST_FOOD;
    }
}