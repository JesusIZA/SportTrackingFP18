package ua.jr.raichuk.WEB.commands.userdo;

import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.DB.entities.impls.Norm;
import ua.jr.raichuk.Exceptions.TransactionException;
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
 * @author Jesus Raichuk
 */
public class TrackingCommand implements Command {
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
            Norm norm = trackingService.getNorm(login);
            Norm forNow = trackingService.getForNow(login);
            List<Food> eatenToday = trackingService.getEatenToday(login);
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
            //reverse list
            List<Food> reversed = new ArrayList<Food>();
            for (int i = eatenToday.size()-1; i >= 0; i--) {
                reversed.add(eatenToday.get(i));
            }
            Integer start = (Integer)request.getSession().getAttribute("start");
            Integer end = (Integer)request.getSession().getAttribute("end");
            if(Objects.isNull(start)) start = 0;
            if(Objects.isNull(end)) end = 10;
            List<Food> page = new ArrayList<Food>();

            String goPage = request.getParameter("doPage");
            System.out.println(goPage);
            if(!Objects.isNull(goPage)) {
                if(goPage.equals("PREV")){
                    start -= 10;
                    end -= 10;
                } else {
                    start += 10;
                    end += 10;
                }
            }
            if(start >= reversed.size()) start = reversed.size() - 10;
            if(start < 0) start = 0;
            if(end < 0) end = 10;
            if(end >= reversed.size()) end = reversed.size();
            System.out.println(start + "-" + end);

            request.getSession().setAttribute("start", start);
            request.getSession().setAttribute("end", end);

            for (int i = start; i < end; i++) {
                page.add(reversed.get(i));
            }

            request.setAttribute("norm", norm);
            request.setAttribute("forNow", forNow);
            request.setAttribute("message", message);
            request.setAttribute("color", color);
            request.setAttribute("foods", foods);
            request.setAttribute("eatenToday", page);
            request.getRequestDispatcher("trackingToday.jsp").forward(request, response);
        } catch (TransactionException e) {
            request.setAttribute("error", "Server error");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }
}
