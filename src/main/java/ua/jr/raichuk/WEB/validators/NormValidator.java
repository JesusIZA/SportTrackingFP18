package ua.jr.raichuk.WEB.validators;

import ua.jr.raichuk.Exceptions.DataException;

/**
 * @author Jesus Raichuk
 */
public abstract class NormValidator {
    public static boolean isValid(int calories, int proteins) throws DataException{
        if(!EnterDataValidator.isValidCaloriesOrProteins(calories)){
            throw new DataException("Calories quantity is incorrect");
        } else if(!EnterDataValidator.isValidCaloriesOrProteins(proteins)) {
            throw new DataException("Proteins quantity is incorrect");
        } else {
            return true;
        }
    }
}
