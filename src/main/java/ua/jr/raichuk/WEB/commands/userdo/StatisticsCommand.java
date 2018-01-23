package ua.jr.raichuk.WEB.commands.userdo;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.services.user.StatisticsService;
import ua.jr.raichuk.WEB.validators.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

/**
 * Command show statistics by the time user need
 *
 * @author Jesus Raichuk
 */
public class StatisticsCommand implements Command {
    private static Logger LOGGER = Logger.getLogger(StatisticsCommand.class);
    public static final int ITEMS_BY_PAGE = 5;
    public static int START_ITEM = 0;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("login");

        Date from = null;
        Date to = null;
        try {
            try {
                from = Date.valueOf(request.getParameter("from"));
                to = Date.valueOf(request.getParameter("to"));
            } catch (IllegalArgumentException e) {
                from = (Date) request.getSession().getAttribute("from");
                to = (Date) request.getSession().getAttribute("to");
            }

            if(Objects.isNull(from)) {
                Date today = Date.valueOf("2018-01-01");
                today.getTime();
                today.setTime(new java.util.Date().getTime());
                from = today;
            }
            if(Objects.isNull(to)) {
                Date today = Date.valueOf("2018-01-01");
                today.getTime();
                today.setTime(new java.util.Date().getTime());
                to = today;
            }
            if(!EnterDataValidator.isValidDate(from) || !EnterDataValidator.isValidDate(to)
                    || !EnterDataValidator.isValidDateRange(from , to)){
                throw new DataException("Range is too long (must be less than 1 month)");
            } else {
                if(!Objects.isNull(request.getSession().getAttribute("startS")) &&
                        !Objects.equals(request.getSession().getAttribute("startS"), "")){
                    START_ITEM = (int) request.getSession().getAttribute("startS");
                }

                String goPage = request.getParameter("doPage");
                if(!Objects.isNull(goPage)) {
                    if(goPage.equals("PREV")){
                        START_ITEM -= ITEMS_BY_PAGE;
                    } else {
                        START_ITEM += ITEMS_BY_PAGE;
                    }
                }

                List<StatisticsService.FoodWithDate> foods = new LinkedList<StatisticsService.FoodWithDate>();
                StatisticsService statisticsService = StatisticsService.getService();
                foods = statisticsService.getFoodsByDateRangeAndLogin(login, from, to);

                if (foods.size() == 0) {
                    Date today = Date.valueOf("2018-01-01");
                    today.getTime();
                    today.setTime(new java.util.Date().getTime());
                    StatisticsService.FoodWithDate fo = new StatisticsService.FoodWithDate(
                            new Food("Please click Search!", 0.0, 0.0, 0.0, 0.0),
                            today);
                    foods.add(fo);
                }
                request.getSession().setAttribute("startS", START_ITEM);

                request.getSession().setAttribute("from", from);
                request.getSession().setAttribute("to", to);

                request.setAttribute("foods", foods);
                request.getRequestDispatcher("statistics.jsp").forward(request, response);
            }
        } catch (DataException e) {
            LOGGER.debug("Command.User->Data (StatisticsCommand.execute()) exception : Data is incorrect!");
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request,response);
        } catch (TransactionException e) {
            LOGGER.error("DB.DAO(CRUD)->Command.User (StatisticsCommand.execute()) exception : read error!");
            request.setAttribute("error", "Server error");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }
}
