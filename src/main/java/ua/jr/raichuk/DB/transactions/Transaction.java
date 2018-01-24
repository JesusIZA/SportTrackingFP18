package ua.jr.raichuk.DB.transactions;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.dao.CRUD;
import ua.jr.raichuk.DB.utils.UtilConnectionPool;
import ua.jr.raichuk.DB.entities.impls.*;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.*;


/**
 * @author Jesus Raichuk
 */
public abstract class Transaction extends UtilConnectionPool {
    private static Logger LOGGER = Logger.getLogger(Transaction.class);

    public static Connection startTransaction() throws TransactionException {
        Connection connection = getConnectionFromConnectionPool();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e2) {
            LOGGER.error("Connection->Transaction (Transaction.startTransaction()) exception : Transaction not started -> setAutocommit error!");
            throw new TransactionException(e2.getMessage());
        }
        return connection;
    }

    public static void rollback(Exception e, Connection connection) throws TransactionException {
        try {
            connection.rollback();
        } catch (SQLException e1) {
            LOGGER.error("Connection->Transaction (Transaction.rollback()) exception : Transaction was not rollback -> rollback error!");
            throw new TransactionException(e1.getMessage());
        } finally {
            LOGGER.error("Connection->Transaction (Transaction.rollback()) exception : Transaction was rollback -> before commit error!");
            throw new TransactionException(e.getMessage());
        }
    }

    public static void commit(Connection connection) throws TransactionException {
        try {
            connection.commit();
        } catch (SQLException e1) {
            LOGGER.error("Connection->Transaction (Transaction.commit()) exception : Transaction not committed -> commit error!");
            throw new TransactionException(e1.getMessage());
        }
    }

    public static void endTransaction(Connection connection) throws TransactionException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e2) {
            LOGGER.error("Connection->Transaction (Transaction.endTransaction()) exception : Transaction not finished -> setAutocommit error!");
            throw new TransactionException(e2.getMessage());
        } finally {
            stopConnection(connection);
        }
    }
}
