package ua.jr.raichuk.Exceptions;

/**
 * @author Jesus Raichuk
 */
public class DBException extends Exception {
    String message;
    public DBException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
