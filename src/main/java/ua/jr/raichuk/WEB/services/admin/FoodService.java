package ua.jr.raichuk.WEB.services.admin;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.DB.entities.impls.WasEaten;
import ua.jr.raichuk.DB.transactions.Transaction;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @author Jesus Raichuk
 */
public class FoodService extends AdminService<Food> {
    private static Logger LOGGER = Logger.getLogger(FoodService.class);

    FoodService(){}
    @Override
    public Entity getEmptyClass() {
        return new Food();
    }

    /**
     * Realized cascading delete from DB for food cortege
     * @param id - cortege id
     * @throws TransactionException
     */
    @Override
    public void delete(int id) throws TransactionException {
        Connection connection = Transaction.startTransaction();
        try {
            DAOFactory.getInstance().getUtilDAO().deleteWasEatenByFoodId(id, connection);
            DAOFactory.getInstance().getCRUD(new Food()).delete(id, connection);
            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.CRUD,DAO (FoodService.delete()) exception : CRUD, UtilDAO for Food delete error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
    }

    /**
     * Realized adding new food to eaten today
     * @param name - food name
     * @param login - user login
     * @throws TransactionException - if has some problem with DB
     */
    public void addFoodToday(String name, String login) throws TransactionException {
        Connection connection = Transaction.startTransaction();
        try {
            Food food = DAOFactory.getInstance().getUtilDAO().findFoodByName(name, connection);

            Link link = DAOFactory.getInstance().getUtilDAO().findLinkByLogin(login, connection);

            Date today = Date.valueOf("1900-01-01");
            today.getTime();
            today.setTime(new java.util.Date().getTime());

            List<WasEaten> wsL = DAOFactory.getInstance().getCRUD(new WasEaten()).findAll(connection);

            WasEaten wasEaten = new WasEaten(link.getIdP(), food.getIdF(), today);

            DAOFactory.getInstance().getCRUD(wasEaten).add(wasEaten, connection);

            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.CRUD,DAO (FoodService.addFoodToday()) exception : CRUD,UtilDAO for Food (WasEaten, Link) adding new food today error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
    }
}
