package ua.jr.raichuk.WEB.commands.userdo;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.DB.entities.impls.Norm;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.Helpers.lists.PrintLists;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.services.user.TrackingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Command show tracking page
 *
 * @author Jesus Raichuk
 */
public class TrackingCommand implements Command {
    private static Logger LOGGER = Logger.getLogger(TrackingCommand.class);
    public static final int ITEMS_BY_PAGE = 5;
    public static int START_ITEM = 0;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("login");
        try {
            TrackingService trackingService = TrackingService.getService();
            String name = "";
            if(!Objects.equals(request.getSession().getAttribute("name"), "")
                    && !Objects.isNull(request.getSession().getAttribute("name"))){
                name = (String)request.getSession().getAttribute("name");
            } else {
                name = trackingService.getName(login) + " " + trackingService.getSurname(login);
                request.getSession().setAttribute("name", name);
            }

            if(!Objects.isNull(request.getSession().getAttribute("startT")) &&
                    !Objects.equals(request.getSession().getAttribute("startT"), "")){
                START_ITEM = (int) request.getSession().getAttribute("startT");
            }

            String goPage = request.getParameter("doPage");
            if(!Objects.isNull(goPage)) {
                if(goPage.equals("PREV")){
                    START_ITEM -= ITEMS_BY_PAGE;
                } else {
                    START_ITEM += ITEMS_BY_PAGE;
                }
            }


            Norm norm = trackingService.getNorm(login);
            Norm forNow = trackingService.getForNow(login);
            List<Food> eatenToday = trackingService.getEatenToday(login, true);
            String message = trackingService.getMessage(norm, forNow);
            String color = trackingService.getColor(norm, forNow);
            List<Food> foods = trackingService.getAllFoods();

            if (eatenToday.size() == 0) {
                Food tf = new Food();
                tf.setName("Nothing today");
                tf.setCalories(0.0);
                tf.setProteins(0.0);
                tf.setFats(0.0);
                tf.setCarbohydrates(0.0);
                eatenToday.add(tf);
            }
            if (foods.size() == 0) {
                Food tf = new Food();
                tf.setName("Nothing");
                tf.setCalories(0.0);
                tf.setProteins(0.0);
                tf.setFats(0.0);
                tf.setCarbohydrates(0.0);
                foods.add(tf);
            }
            request.getSession().setAttribute("startT", START_ITEM);

            request.setAttribute("norm", norm);
            request.setAttribute("forNow", forNow);
            request.setAttribute("message", message);
            request.setAttribute("color", color);
            request.setAttribute("foods", foods);
            request.setAttribute("eatenToday", eatenToday);
            request.getRequestDispatcher("trackingToday.jsp").forward(request, response);
        } catch (TransactionException e) {
            LOGGER.error("DB.DAO(CRUD)->Command.User (TrackingCommand.execute()) exception : read error!");
            request.setAttribute("error", "Server error");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }
}
