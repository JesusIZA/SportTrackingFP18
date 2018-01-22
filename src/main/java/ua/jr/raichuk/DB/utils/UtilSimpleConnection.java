package ua.jr.raichuk.DB.utils;

import org.apache.log4j.Logger;
import ua.jr.raichuk.Exceptions.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Jesus Raichuk
 */
public abstract class UtilSimpleConnection {
    private static Logger LOGGER = Logger.getLogger(UtilSimpleConnection.class);

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/sportt18";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private static Connection connection = null;

    public static Connection getConnection(){
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            LOGGER.error("Connection (UtilSimpleConnection.getConnection()) exception : Connection cannot be got!");
        }
        return connection;
    }
    public static void stopConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            LOGGER.error("Connection (UtilSimpleConnection.stopConnection()) exception : Connection cannot be stopped!");
            try {
                throw new ConnectionException(e);
            } catch (ConnectionException e1) {
                LOGGER.debug("Connection (UtilSimpleConnection.stopConnection()) exception : Connection stop error!");
            }
        }
    }
}
