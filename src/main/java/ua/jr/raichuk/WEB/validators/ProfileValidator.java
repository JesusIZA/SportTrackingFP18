package ua.jr.raichuk.WEB.validators;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.impls.Norm;
import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.Date;

/**
 * @author Jesus Raichuk
 */
public abstract class ProfileValidator {
    private static Logger LOGGER = Logger.getLogger(ProfileValidator.class);

    public static boolean isIdPExisting(int id) throws DataException {
        try {
            return EnterDataValidator.isIdExisting(id, new Profile());
        } catch (TransactionException e) {
            LOGGER.debug("DataValidator.Profile (ProfileValidator.isIdPExisting()) exception : Profile data is incorrect!");
            throw new DataException("Id profile not found");
        }
    }
    public static boolean isValid(String name, String surname, String sex, Date birthday, double height, double weight, int activeTime) throws DataException{
        if (!EnterDataValidator.isValidNameOrSurname(name)) {
            throw new DataException("Name is incorrect");
        } else if (!EnterDataValidator.isValidNameOrSurname(surname)) {
            throw new DataException("Surname is incorrect");
        }  else if (!EnterDataValidator.isValidSex(sex)) {
            throw new DataException("Sex is incorrect");
        } else if (!EnterDataValidator.isValidDate(birthday)) {
            throw new DataException("Birthday is incorrect");
        } else if (!EnterDataValidator.isValidHeightOrWeight(height)) {
            throw new DataException("Height is incorrect");
        } else if (!EnterDataValidator.isValidHeightOrWeight(weight)) {
            throw new DataException("Weight is incorrect");
        } else if (!EnterDataValidator.isValidActiveTime(activeTime)) {
            throw new DataException("Active time is incorrect");
        } else {
            return true;
        }
    }
}
