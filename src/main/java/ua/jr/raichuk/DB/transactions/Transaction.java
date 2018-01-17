package ua.jr.raichuk.DB.transactions;

import ua.jr.raichuk.DB.dao.CRUD;
import ua.jr.raichuk.DB.utils.UtilConnectionPool;
import ua.jr.raichuk.DB.entities.impls.*;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.*;


/**
 * @author Jesus Raichuk
 */
public abstract class Transaction extends UtilConnectionPool {
    public static Connection startTransaction() throws TransactionException {
        Connection connection = getConnectionFromConnectionPool();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e2) {
            System.out.println(e2.getMessage());
            throw new TransactionException(e2.getMessage());
        }
        return connection;
    }

    public static void rollback(Exception e, Connection connection) throws TransactionException {
        try {
            connection.rollback();
        } catch (SQLException e1) {
            System.out.println(e.getMessage());
            throw new TransactionException(e1.getMessage());
        } finally {
            e.printStackTrace();
            throw new TransactionException(e.getMessage());
        }
    }

    public static void commit(Connection connection) throws TransactionException {
        try {
            connection.commit();
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
            throw new TransactionException(e1.getMessage());
        }
    }

    public static void endTransaction(Connection connection) throws TransactionException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e2) {
            System.out.println(e2.getMessage());
            throw new TransactionException(e2.getMessage());
        } finally {
            stopConnection(connection);
        }
    }
}
