package ua.jr.raichuk.WEB.commands.userdo;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.services.admin.AdminServiceFactory;
import ua.jr.raichuk.WEB.services.admin.FoodService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command add new item to WasEaten with today date and profile id into table in DB if entered data is valid
 *
 * @author Jesus Raichuk
 */
public class AddFoodTodayCommand implements Command {
    private static Logger LOGGER = Logger.getLogger(AddFoodTodayCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("login");
        try {
            String food = request.getParameter("foodItem");

            FoodService foodService = (FoodService) AdminServiceFactory.getAdminService(new Food());
            foodService.addFoodToday(food, login);
            FactoryCommand.getInstance().getCommand(FactoryCommand.TRACKING).execute(request,response);
        } catch (TransactionException e) {
            LOGGER.error("DB.DAO(CRUD)->Command.User (AddFoodTodayCommand.execute()) exception : insert error!");
            request.setAttribute("error", "Server error");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }
}
