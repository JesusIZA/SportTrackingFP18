package ua.jr.raichuk.WEB.services.admin;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.transactions.Transaction;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for admin use (work with DB tables - corteges)
 *
 * @author Jesus Raichuk
 */
public abstract class AdminService<T> {
    private static Logger LOGGER = Logger.getLogger(AdminService.class);

    /**
     * Add new cortege to DB
     * @param t - cortege
     * @throws TransactionException - if it is not posible
     */
    public void add(T t) throws TransactionException {
        Connection connection = Transaction.startTransaction();
        try {
            DAOFactory.getInstance().getCRUD((Entity)t).add(t, connection);
            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.CRUD (AdminService.add()) exception : CRUD for " + ((Entity)t).getClassName() + " adding error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
    }
    /**
     * Update cortege into DB
     * @param t - cortege
     * @throws TransactionException - if it is not posible
     */
    public void update(T t) throws TransactionException {
        Connection connection = Transaction.startTransaction();
        try {
            DAOFactory.getInstance().getCRUD((Entity) t).update(t, connection);
            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.CRUD (AdminService.update()) exception : CRUD for " + ((Entity)t).getClassName() + " update error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
    }
    /**
     * Delete cortege from DB
     * @param id - cortege id
     * @throws TransactionException - if it is not posible
     */
    public void delete(int id) throws TransactionException {
        Connection connection = Transaction.startTransaction();
        try {
            DAOFactory.getInstance().getCRUD(getEmptyClass()).delete(id, connection);
            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.CRUD (AdminService.delete()) exception : CRUD for " + getEmptyClass().getClassName() + "with id=" + id + " delete error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
    }
    /**
     * Get all corteges in DB
     * @throws TransactionException - if it is not posible
     */
    public List<T> getAll() throws TransactionException {
        Connection connection = Transaction.startTransaction();
        List<T> ts = new ArrayList<T>();
        try {
            ts = DAOFactory.getInstance().getCRUD(getEmptyClass()).findAll(connection);
            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.CRUD (AdminService.getAll()) exception : CRUD for " + getEmptyClass().getClassName() + " geAll error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        return ts;
    }
    /**
     * Get cortege from DB by id
     * @param id - cortege id
     * @throws TransactionException - if it is not posible
     */
    public T getById(int id) throws TransactionException {
        Connection connection = Transaction.startTransaction();
        T t = (T) getEmptyClass();
        try {
            t = (T) DAOFactory.getInstance().getCRUD((Entity)t).findById(id, connection);
            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.CRUD (AdminService.getById()) exception : CRUD for " + getEmptyClass().getClassName() + " with id=" + id + " getById error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        return t;
    }

    public abstract Entity getEmptyClass();
}
