package ua.jr.raichuk.WEB.validators;

import org.junit.Test;
import ua.jr.raichuk.Exceptions.DataException;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WasEatenValidatorTest {
    @Test
    public void isValidGood() throws Exception {
        java.sql.Date dateSQL = java.sql.Date.valueOf("2000-01-01");
        java.util.Date today = new java.util.Date();
        dateSQL.setTime(today.getTime());
        assertTrue(WasEatenValidator.isValid(dateSQL));
    }

    @Test
    public void isValidLessMinDate() throws Exception {
        boolean flag = false;
        try{
            WasEatenValidator.isValid(java.sql.Date.valueOf("1000-02-01"));
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidMoreMaxDate() throws Exception {
        boolean flag = false;
        try{
            java.sql.Date dateSQL = java.sql.Date.valueOf("2000-01-01");
            java.util.Date today = new java.util.Date();
            dateSQL.setTime(today.getTime() + 31560000000L);
            WasEatenValidator.isValid(dateSQL);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

}