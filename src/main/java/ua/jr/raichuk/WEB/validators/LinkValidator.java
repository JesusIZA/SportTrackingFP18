package ua.jr.raichuk.WEB.validators;

import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;

/**
 * @author Jesus Raichuk
 */
public abstract class LinkValidator {
    public static boolean isIdLExisting(int id) throws DataException {
        try {
            return EnterDataValidator.isIdExisting(id, new User());
        } catch (TransactionException e) {
            throw new DataException("Id link not found");
        }
    }

}
