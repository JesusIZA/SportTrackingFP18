package ua.jr.raichuk.WEB.validators;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.impls.WasEaten;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.Date; /**
 * @author Jesus Raichuk
 */
public abstract class WasEatenValidator {
    private static Logger LOGGER = Logger.getLogger(WasEatenValidator.class);

    /**
     * Get true if id does exist and false if does not exist
     * @param id - id will search
     * @return boolean value - exist or not
     * @throws DataException - id data is incorrect
     */
    public static boolean isIdWEExisting(int id) throws DataException {
        try {
            return EnterDataValidator.isIdExisting(id, new WasEaten());
        } catch (TransactionException e) {
            LOGGER.debug("DataValidator.WasEaten (WasEatenValidator.isIdWEExisting()) exception : WasEaten data is incorrect!");
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
