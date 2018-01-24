package ua.jr.raichuk.WEB.validators;

import org.junit.Test;
import ua.jr.raichuk.Exceptions.DataException;

import static org.junit.Assert.*;

public class FoodValidatorTest {
    @Test
    public void isValidGood() throws Exception {
        assertTrue(FoodValidator.isValid("Test name", 123.3, 12.3, 32.2, 44.3));
        assertTrue(FoodValidator.isValid("Тестове name", 123.3, 12.3, 32.2, 44.3));
        assertTrue(FoodValidator.isValid("12 Тестове name", 123.3, 12.3, 32.2, 44.3));
        assertTrue(FoodValidator.isValid("_12 Тестове name", 123.3, 12.3, 32.2, 44.3));
    }

    @Test
    public void isValidBadName() throws Exception {
        boolean flag = false;
        try{
            FoodValidator.isValid("^Test name", 123.3, 12.3, 32.2, 44.3);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidLessMinCalories() throws Exception {
        boolean flag = false;
        try{
            FoodValidator.isValid("Test name", -1, 12.3, 32.2, 44.3);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidMoreMaxCalories() throws Exception {
        boolean flag = false;
        try{
            FoodValidator.isValid("Test name", 1000, 12.3, 32.2, 44.3);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidLessMinProteins() throws Exception {
        boolean flag = false;
        try{
            FoodValidator.isValid("Test name", 123.1, -1, 32.2, 44.3);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidMoreMaxProteins() throws Exception {
        boolean flag = false;
        try{
            FoodValidator.isValid("Test name", 132.0, 1000, 32.2, 44.3);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidLessMinFats() throws Exception {
        boolean flag = false;
        try{
            FoodValidator.isValid("Test name", 123.1, 1.2, -1, 44.3);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidMoreMaxFats() throws Exception {
        boolean flag = false;
        try{
            FoodValidator.isValid("Test name", 132.0, 12.3, 1000, 44.3);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidLessMinCarbohydrates() throws Exception {
        boolean flag = false;
        try{
            FoodValidator.isValid("Test name", 123.1, 1.2, 21.3, -1);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidMoreMaxCarbohydrates() throws Exception {
        boolean flag = false;
        try{
            FoodValidator.isValid("Test name", 132.0, 12.3, 32.2, 1000);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

}