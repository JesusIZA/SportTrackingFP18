package ua.jr.raichuk.WEB.validators;

import ua.jr.raichuk.Exceptions.DataException;

import java.sql.Date; /**
 * @author Jesus Raichuk
 */
public abstract class WasEatenValidator {
    public static boolean isValid(Date date) throws DataException {
        if(!EnterDataValidator.isValidDate(date)) {
            throw new DataException("Date is incorrect");
        } else {
            return true;
        }
    }
}
