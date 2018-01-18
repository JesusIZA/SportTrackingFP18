package ua.jr.raichuk.WEB.validators;

import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;

/**
 * @author Jesus Raichuk
 */
public abstract class FoodValidator {
    public static boolean isIdFExisting(int id) throws DataException {
        try {
            return EnterDataValidator.isIdExisting(id, new Food());
        } catch (TransactionException e) {
            throw new DataException("Id food not found");
        }
    }
    public static boolean isValid(String name, double calories, double proteins, double fats, double carbohydrates) throws DataException{
        if(!EnterDataValidator.isValidFoodName(name)) {
            throw new DataException("Name of food is incorrect");
        } else if(!EnterDataValidator.isValidCaloriesOrProteinsOrFatsOrCarbohydrates(calories)){
            throw new DataException("Calories quantity of food is incorrect");
        } else if(!EnterDataValidator.isValidCaloriesOrProteinsOrFatsOrCarbohydrates(proteins)){
            throw new DataException("Proteins quantity of food is incorrect");
        } else if(!EnterDataValidator.isValidCaloriesOrProteinsOrFatsOrCarbohydrates(fats)){
            throw new DataException("Fats quantity of food is incorrect");
        } else if(!EnterDataValidator.isValidCaloriesOrProteinsOrFatsOrCarbohydrates(carbohydrates)) {
            throw new DataException("Calories quantity of food is incorrect");
        } else {
            return true;
        }
    }
}
