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
 * @author Jesus Raichuk
 */
public class StatisticsCommand implements Command {
    private static Logger LOGGER = Logger.getLogger(StatisticsCommand.class);
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
                System.out.println("session");
                from = (Date) request.getSession().getAttribute("from");
                to = (Date) request.getSession().getAttribute("to");
            }
                System.out.println("from=" + from);
                System.out.println("to=" + to);

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
                throw new DataException("Date is incorrect");
            } else {
                Set<Date> dates = new HashSet<>();
                Map<Date, List<Food>> foods = new HashMap<Date, List<Food>>();

                StatisticsService statisticsService = StatisticsService.getService();
                dates = statisticsService.getAllDatesRangeByLogin(login, from, to);
                foods = statisticsService.getFoodsByDateRangeAndLogin(login, from, to);

                List<Date> datesL = new ArrayList<Date>();

                Iterator<Date> iterator = dates.iterator();
                while (iterator.hasNext()){
                    datesL.add(iterator.next());
                }
                //sorting
                for (int i = 0; i < datesL.size()-1; i++) {
                    if(datesL.get(i).getTime() > datesL.get(i+1).getTime()){
                        Long temp = datesL.get(i).getTime();
                        datesL.get(i).setTime(datesL.get(i+1).getTime());
                        datesL.get(i+1).setTime(temp);
                    }
                }

                if(datesL.size() == 0) {
                    datesL.add(Date.valueOf("1900-01-01"));
                }
                if(foods.size() == 0) {
                    List<Food> fo = Arrays.asList(new Food("Please click Search!", 0.0,0.0,0.0,0.0));
                    foods.put(Date.valueOf("1900-01-01"), fo);
                }

                Integer pos = (Integer)request.getSession().getAttribute("pos");
                if(Objects.isNull(pos)) pos = 0;

                String goPage = request.getParameter("doPage");
                System.out.println(goPage);
                if(!Objects.isNull(goPage)) {
                    if(goPage.equals("PREV")){
                        pos -= 1;
                    } else {
                        pos += 1;
                    }
                }
                if(pos >= datesL.size()) pos = datesL.size() - 1;
                if(pos < 0) pos = 0;

                Date date = datesL.get(pos);
                List<Food> foodsD = foods.get(date);

                request.getSession().setAttribute("from", from);
                request.getSession().setAttribute("to", to);
                request.getSession().setAttribute("pos", pos);

                request.setAttribute("date", date);
                request.setAttribute("foods", foodsD);
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
