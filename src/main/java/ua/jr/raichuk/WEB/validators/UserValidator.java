package ua.jr.raichuk.WEB.validators;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;

/**
 * @author Jesus Raichuk
 */
public abstract class UserValidator {
    private static Logger LOGGER = Logger.getLogger(UserValidator.class);

    public static boolean isIdUExisting(int id) throws DataException {
        try {
            return EnterDataValidator.isIdExisting(id, new User());
        } catch (TransactionException e) {
            LOGGER.debug("DataValidator.User (UserValidator.isIdUExisting()) exception : User data is incorrect!");
            throw new DataException("Id user not found");
        }
    }

    public static boolean isValid(String login, String password) throws DataException {
        if (!EnterDataValidator.isValidLogin(login)) {
            throw new DataException("Login is incorrect");
        } else if (!EnterDataValidator.isValidPassword(password)) {
            throw new DataException("Password is incorrect");
        } else {
            return true;
        }
    }

}
