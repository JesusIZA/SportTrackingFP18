package ua.jr.raichuk.WEB.validators;

import org.junit.Test;
import ua.jr.raichuk.Exceptions.DataException;

import java.sql.Date;

import static org.junit.Assert.*;

public class ProfileValidatorTest {
    @Test
    public void isValidGood() throws Exception {
        assertTrue(ProfileValidator.isValid("Test", "Тест", "female", Date.valueOf("2008-09-08"), 187, 89, 35));
    }

    @Test
    public void isValidLessMinActiveTime() throws Exception {
        boolean flag = false;
        try{
            ProfileValidator.isValid("Test", "Тест", "female", Date.valueOf("2008-09-08"), 185, 89, 100);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidMoreMaxActiveTime() throws Exception {
        boolean flag = false;
        try{
            ProfileValidator.isValid("Test", "Тест", "female", Date.valueOf("2008-09-08"), 185, 89, -1);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidLessMinWeight() throws Exception {
        boolean flag = false;
        try{
            ProfileValidator.isValid("Test", "Тест", "female", Date.valueOf("2008-09-08"), 185, -1, 35);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidMoreMaxWeight() throws Exception {
        boolean flag = false;
        try{
            ProfileValidator.isValid("Test", "Тест", "female", Date.valueOf("2008-09-08"), 185, 1000, 35);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidLessMinHeight() throws Exception {
        boolean flag = false;
        try{
            ProfileValidator.isValid("Test", "Тест", "female", Date.valueOf("2008-09-08"), -0, 89, 35);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidMoreMaxHeight() throws Exception {
        boolean flag = false;
        try{
            ProfileValidator.isValid("Test", "Тест", "female", Date.valueOf("2008-09-08"), 1000, 89, 35);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidLessMinDate() throws Exception {
        boolean flag = false;
        try{
            ProfileValidator.isValid("Test", "Тест", "female", Date.valueOf("1000-09-08"), 187, 89, 35);
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
            ProfileValidator.isValid("Test", "Тест", "female", dateSQL, 187, 89, 35);
        } catch (DataException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void isValidBadSex() throws Exception {
        int flag = 0;
        try {
            ProfileValidator.isValid("Тест", "Тест", "female2", Date.valueOf("2008-09-08"), 187, 89, 35);
        } catch (DataException e) {
            flag++;
        }
        try {
            ProfileValidator.isValid("Тест", "Тест", "чол", Date.valueOf("2008-09-08"), 187, 89, 35);
        } catch (DataException e) {
            flag++;
        }
        try {
            ProfileValidator.isValid("Тест", "Тест", "emale", Date.valueOf("2008-09-08"), 187, 89, 35);
        } catch (DataException e) {
            flag++;
        }
        try {
            ProfileValidator.isValid("Тестest", "Тест", "fe5;le", Date.valueOf("2008-09-08"), 187, 89, 35);
        } catch (DataException e) {
            flag++;
        }
        assertTrue(flag == 4);
    }

    @Test
    public void isValidBadName() throws Exception {
        int flag = 0;
        try {
            ProfileValidator.isValid("Test test", "Тест", "female", Date.valueOf("2008-09-08"), 187, 89, 35);
        } catch (DataException e) {
            flag++;
        }
        try {
            ProfileValidator.isValid("Test_est", "Тест", "female", Date.valueOf("2008-09-08"), 187, 89, 35);
        } catch (DataException e) {
            flag++;
        }
        try {
            ProfileValidator.isValid("Te5est", "Тест", "female", Date.valueOf("2008-09-08"), 187, 89, 35);
        } catch (DataException e) {
            flag++;
        }
        try {
            ProfileValidator.isValid("Tes#est", "Тест", "female", Date.valueOf("2008-09-08"), 187, 89, 35);
        } catch (DataException e) {
            flag++;
        }
        assertTrue(flag == 4);
    }

    @Test
    public void isValidBadSurname() throws Exception {
        int flag = 0;
        try {
            ProfileValidator.isValid("Test","Test test",  "female", Date.valueOf("2008-09-08"), 187, 89, 35);
        } catch (DataException e) {
            flag++;
        }
        try {
            ProfileValidator.isValid("Test","Test_est",  "female", Date.valueOf("2008-09-08"), 187, 89, 35);
        } catch (DataException e) {
            flag++;
        }
        try {
            ProfileValidator.isValid("Test","Te5est", "female", Date.valueOf("2008-09-08"), 187, 89, 35);
        } catch (DataException e) {
            flag++;
        }
        try {
            ProfileValidator.isValid("Test","Tes#est", "female", Date.valueOf("2008-09-08"), 187, 89, 35);
        } catch (DataException e) {
            flag++;
        }
        assertTrue(flag == 4);
    }

}