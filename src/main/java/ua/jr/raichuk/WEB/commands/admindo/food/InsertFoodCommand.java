package ua.jr.raichuk.WEB.commands.admindo.food;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminInsertCommand;
import ua.jr.raichuk.WEB.services.admin.AdminService;
import ua.jr.raichuk.WEB.services.admin.FoodService;
import ua.jr.raichuk.WEB.validators.FoodValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jesus Raichuk
 */
public class InsertFoodCommand extends AdminInsertCommand<Food> {
    @Override
    protected Entity getEntity() {
        return new Food();
    }

    @Override
    protected String getRedirect() {
        return FactoryCommand.ADMIN_SHOW_LIST_FOOD;
    }

    @Override
    protected Food getValidEntity(HttpServletRequest request) throws DataException {
        String name = request.getParameter("nameF");
        Double calories = Double.valueOf(request.getParameter("caloriesF"));
        Double proteins = Double.valueOf(request.getParameter("proteinsF"));
        Double fats = Double.valueOf(request.getParameter("fatsF"));
        Double carbohydrates = Double.valueOf(request.getParameter("carbohydratesF"));

        if (FoodValidator.isValid(name, calories, proteins, fats, carbohydrates)) {
            Food food = new Food(name, calories, proteins, fats, carbohydrates);
            return food;
        } else {
            throw new DataException("Data is incorrect");
        }
    }
}
