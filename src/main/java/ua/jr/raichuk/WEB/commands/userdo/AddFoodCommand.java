package ua.jr.raichuk.WEB.commands.userdo;

import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.services.admin.AdminServiceFactory;
import ua.jr.raichuk.WEB.services.admin.FoodService;
import ua.jr.raichuk.WEB.validators.FoodValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jesus Raichuk
 */
public class AddFoodCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("login");
        try {
            String name = request.getParameter("nameFood");
            Double calories = Double.valueOf(request.getParameter("calories"));
            Double proteins = Double.valueOf(request.getParameter("proteins"));
            Double fats = Double.valueOf(request.getParameter("fats"));
            Double carbohydrates = Double.valueOf(request.getParameter("carbohydrates"));

            if(FoodValidator.isValid(name, calories, proteins, fats, carbohydrates)){
                Food food = new Food(name, calories, proteins, fats, carbohydrates);

                FoodService foodService = (FoodService) AdminServiceFactory.getAdminService(food);
                foodService.add(food);
                FactoryCommand.getInstance().getCommand(FactoryCommand.TRACKING).execute(request, response);
            }
        } catch (DataException e1) {
            request.setAttribute("error", e1.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request,response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Data is illegal");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        } catch (TransactionException e) {
            request.setAttribute("error", "Server error");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }
}
