package ua.jr.raichuk.DB.utils;

import ua.jr.raichuk.Exceptions.ConnectionException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Jesus Raichuk
 */
public abstract class UtilConnectionPool {
    private static DataSource dataSource = null;
    private static final String JNDI_LOOKUP_SERVICE = "java:comp/env/jdbc/sportt18db";

    public static DataSource getDataSource(){
        try {
            Context context = new InitialContext();
            Object lookup = context.lookup(JNDI_LOOKUP_SERVICE);
            if(lookup!=null) {
                dataSource = (DataSource) lookup;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    public static Connection getConnectionFromConnectionPool() {
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
        } catch (SQLException e) {
            try {
                throw new ConnectionException(e);
            } catch (ConnectionException e1) {
                e1.printStackTrace();
            }
        }
        return connection;
    }

    public static void stopConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            try {
                throw new ConnectionException(e);
            } catch (ConnectionException e1) {
                e1.printStackTrace();
            }
        }
    }
}
