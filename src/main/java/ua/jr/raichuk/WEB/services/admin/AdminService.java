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
 * @author Jesus Raichuk
 */
public abstract class AdminService<T> {
    private static Logger LOGGER = Logger.getLogger(AdminService.class);
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
