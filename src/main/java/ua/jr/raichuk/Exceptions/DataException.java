package ua.jr.raichuk.Exceptions;

/**
 * @author Jesus Raichuk
 */
public class DataException extends Exception {
    private String message;
    public DataException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
