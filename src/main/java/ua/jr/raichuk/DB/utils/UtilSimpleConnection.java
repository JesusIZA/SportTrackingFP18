package ua.jr.raichuk.DB.utils;

import ua.jr.raichuk.Exceptions.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Jesus Raichuk
 */
public abstract class UtilSimpleConnection {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sportt18db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private static Connection connection = null;

    public static Connection getConnection(){
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection OK!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection ERROR!");
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
