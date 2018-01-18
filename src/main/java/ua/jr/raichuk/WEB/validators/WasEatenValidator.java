package ua.jr.raichuk.WEB.validators;

import ua.jr.raichuk.DB.entities.impls.WasEaten;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.Date; /**
 * @author Jesus Raichuk
 */
public abstract class WasEatenValidator {
    public static boolean isIdWEExisting(int id) throws DataException {
        try {
            return EnterDataValidator.isIdExisting(id, new WasEaten());
        } catch (TransactionException e) {
            throw new DataException("Id was eaten not found");
        }
    }
    public static boolean isValid(Date date) throws DataException {
        if(!EnterDataValidator.isValidDate(date)) {
            throw new DataException("Date is incorrect");
        } else {
            return true;
        }
    }
}
