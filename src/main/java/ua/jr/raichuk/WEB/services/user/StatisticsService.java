package ua.jr.raichuk.WEB.services.user;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.DB.entities.impls.WasEaten;
import ua.jr.raichuk.DB.transactions.Transaction;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.Helpers.lists.PrintLists;
import ua.jr.raichuk.WEB.commands.userdo.StatisticsCommand;

import java.sql.Connection;
import java.sql.Date;
import java.util.*;

/**
 * @author Jesus Raichuk
 */
public class StatisticsService {
    private static Logger LOGGER = Logger.getLogger(StatisticsService.class);

    private static StatisticsService service = new StatisticsService();

    public static StatisticsService getService(){
        return service;
    }

    public static int items;

    private StatisticsService(){}

    /**
     * Get all foods were eaten user by login is needed
     * @param login - user login
     * @param from - start date
     * @param to - end date
     * @return List with foods and date when it were eaten
     * @throws TransactionException - if has some problem with DB
     */
    public List<FoodWithDate> getFoodsByDateRangeAndLogin(String login, Date from, Date to) throws TransactionException {
        List<FoodWithDate> fWD = new LinkedList<FoodWithDate>();
        Connection connection = Transaction.startTransaction();
        try{
            Link link = DAOFactory.getInstance().getUtilDAO().findLinkByLogin(login, connection);

            List<WasEaten> wasEatenList = DAOFactory.getInstance().getUtilDAO().findWasEatenByProfileId(link.getIdP(), connection);

            List<Date> dates = getAllDatesRangeByLogin(login, from, to);

            List<FoodWithDate> allByRange = new ArrayList<FoodWithDate>();
            for (int i = 0; i < dates.size(); i++) {
                for (int j = 0; j < wasEatenList.size(); j++) {
                    if(wasEatenList.get(j).getDateWE().getTime() == dates.get(i).getTime()) {
                        Food food = (Food) DAOFactory.getInstance().getCRUD(new Food()).findById(wasEatenList.get(j).getIdF(), connection);
                        FoodWithDate temp = new FoodWithDate(food, dates.get(i));
                        allByRange.add(temp);
                    }
                }
            }

            //Pagination
            if (StatisticsCommand.START_ITEM == allByRange.size())
                StatisticsCommand.START_ITEM = allByRange.size() - StatisticsCommand.ITEMS_BY_PAGE;
            if(StatisticsCommand.START_ITEM > allByRange.size())
                StatisticsCommand.START_ITEM = allByRange.size() - (allByRange.size()%StatisticsCommand.ITEMS_BY_PAGE);
            if(StatisticsCommand.START_ITEM < 0)
                StatisticsCommand.START_ITEM = 0;

            for (int i = StatisticsCommand.START_ITEM; i < allByRange.size() && i < StatisticsCommand.START_ITEM + StatisticsCommand.ITEMS_BY_PAGE; i++) {
                fWD.add(allByRange.get(i));
            }

            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.DAO,CRUD (StatisticsService.getFoodsByDateRangeAndLogin()) exception : UtilDAO,CRUD reading error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        return fWD;
    }

    /**
     * Get all dates when user had some food
     * @param login - user login
     * @param from - start date
     * @param to - end date
     * @return List of dates
     * @throws TransactionException - if has some problem with DB
     */
    public List<Date> getAllDatesRangeByLogin(String login, Date from, Date to) throws TransactionException {
        Set<Date> dates = new HashSet<Date>();
        Connection connection = Transaction.startTransaction();
        try{
            Link link = DAOFactory.getInstance().getUtilDAO().findLinkByLogin(login, connection);
            List<WasEaten> wasEatenList = DAOFactory.getInstance().getUtilDAO().findWasEatenByProfileId(link.getIdP(), connection);
            for (int i = 0; i < wasEatenList.size(); i++) {

                if(wasEatenList.get(i).getDateWE().getTime() >= from.getTime() &&
                        wasEatenList.get(i).getDateWE().getTime() <= to.getTime()){
                    dates.add(wasEatenList.get(i).getDateWE());
                }
            }
            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.DAO (StatisticsService.getAllDatesRangeByLogin()) exception : UtilDAO reading error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        Date[] datesD = new Date[dates.size()];
        List<Date> ret = Arrays.asList(dates.toArray(datesD));
        Collections.sort(ret);
        return ret;
    }

    /**
     * Inner class realize food and date when one was eaten
     */
    public static class FoodWithDate extends Food{
        private Date date;
        public FoodWithDate(Food food, Date date) {
            super(food.getName(), food.getCalories(), food.getProteins(), food.getFats(), food.getCarbohydrates());
            this.date = date;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
