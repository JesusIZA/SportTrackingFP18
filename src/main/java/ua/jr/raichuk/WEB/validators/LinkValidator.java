package ua.jr.raichuk.WEB.validators;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;

/**
 * @author Jesus Raichuk
 */
public abstract class LinkValidator {
    private static Logger LOGGER = Logger.getLogger(LinkValidator.class);

    public static boolean isIdLExisting(int id) throws DataException {
        try {
            return EnterDataValidator.isIdExisting(id, new User());
        } catch (TransactionException e) {
            LOGGER.debug("DataValidator.Link (LinkValidator.isIdLExisting()) exception : Link data is incorrect!");
            throw new DataException("Id link not found");
        }
    }

}
