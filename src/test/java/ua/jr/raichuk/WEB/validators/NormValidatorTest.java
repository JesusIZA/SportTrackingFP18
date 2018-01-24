package ua.jr.raichuk.WEB.validators;

import org.junit.Test;
import ua.jr.raichuk.Exceptions.DataException;

import static org.junit.Assert.assertTrue;

public class NormValidatorTest {
    @Test
    public void isValidGood() throws Exception {
           assertTrue(NormValidator.isValid(4000, 322));
    }

    @Test
    public void isValidLessMinCalories() throws Exception {
        boolean flag = false;
        try{
            NormValidator.isValid(-1, 235);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidMoreMaxCalories() throws Exception {
        boolean flag = false;
        try{
            NormValidator.isValid(10001, 325);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidLessMinProteins() throws Exception {
        boolean flag = false;
        try{
            NormValidator.isValid(4000, -1);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidMoreMaxProteins() throws Exception {
        boolean flag = false;
        try{
            NormValidator.isValid(4000, 1001);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }



}