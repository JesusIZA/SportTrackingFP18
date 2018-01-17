package ua.jr.raichuk.Exceptions;

import java.sql.SQLException;

/**
 * @author Jesus Raichuk
 */
public class ConnectionException extends Exception {
    public ConnectionException(NullPointerException e) {

    }
    public ConnectionException(SQLException e) {

    }
}
