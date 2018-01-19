package ua.jr.raichuk.WEB.services.user;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.DB.entities.impls.WasEaten;
import ua.jr.raichuk.DB.transactions.Transaction;
import ua.jr.raichuk.Exceptions.TransactionException;

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

    private StatisticsService(){}

    public Map<Date, List<Food>> getFoodsByDateRangeAndLogin(String login, Date from, Date to) throws TransactionException {
        Map ret = new HashMap<String, List<Food>>();
        Connection connection = Transaction.startTransaction();
        try{
            Link link = DAOFactory.getInstance().getUtilDAO().findLinkByLogin(login, connection);

            List<WasEaten> wasEatenList = DAOFactory.getInstance().getUtilDAO().findWasEatenByProfileId(link.getIdP(), connection);

            Set<Date> dates = getAllDatesRangeByLogin(login, from, to);
            Iterator<Date> iterator = dates.iterator();


            for (int i = 0; i < dates.size(); i++) {
                List<Food> foods = new ArrayList<Food>();
                Date d = iterator.next();
                for (int j = 0; j < wasEatenList.size(); j++) {
                    if(wasEatenList.get(j).getDateWE().getTime() == d.getTime()) {
                        Food food = (Food) DAOFactory.getInstance().getCRUD(new Food()).findById(wasEatenList.get(j).getIdF(), connection);
                        foods.add(food);
                    }
                }
                ret.put(d, foods);
            }

            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.DAO,CRUD (StatisticsService.getFoodsByDateRangeAndLogin()) exception : UtilDAO,CRUD reading error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        return ret;
    }

    public Set<Date> getAllDatesRangeByLogin(String login, Date from, Date to) throws TransactionException {
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
        return dates;
    }
}
