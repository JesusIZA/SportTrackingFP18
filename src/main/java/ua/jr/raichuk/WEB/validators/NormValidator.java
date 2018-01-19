package ua.jr.raichuk.WEB.validators;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.impls.Norm;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;

/**
 * @author Jesus Raichuk
 */
public abstract class NormValidator {
    private static Logger LOGGER = Logger.getLogger(NormValidator.class);

    public static boolean isIdNExisting(int id) throws DataException {
        try {
            return EnterDataValidator.isIdExisting(id, new Norm());
        } catch (TransactionException e) {
            LOGGER.debug("DataValidator.Norm (NormValidator.isIdNExisting()) exception : Norm data is incorrect!");
            throw new DataException("Id norm not found");
        }
    }
    public static boolean isValid(int calories, int proteins) throws DataException{
        if(!EnterDataValidator.isValidNormCalories(calories)){
            throw new DataException("Calories quantity is incorrect");
        } else if(!EnterDataValidator.isValidNormProteins(proteins)) {
            throw new DataException("Proteins quantity is incorrect");
        } else {
            return true;
        }
    }
}
