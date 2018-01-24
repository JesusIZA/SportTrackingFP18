package ua.jr.raichuk.Exceptions;

/**
 * @author Jesus Raichuk
 */
public class TransactionException extends Exception {
    String message;
    public TransactionException(String message) {
        this.message = message;
    }

    public TransactionException() {
        message = "Transaction Exception";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
