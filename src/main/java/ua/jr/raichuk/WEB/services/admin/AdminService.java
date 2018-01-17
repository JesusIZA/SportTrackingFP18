package ua.jr.raichuk.WEB.services.admin;

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
    public void add(T t) throws TransactionException {
        Connection connection = Transaction.startTransaction();
        try {
            DAOFactory.getInstance().getCRUD((Entity)t).add(t, connection);
            Transaction.commit(connection);
        } catch (Exception e) {
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
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        return t;
    }

    public abstract Entity getEmptyClass();
}
