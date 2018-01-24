package ua.jr.raichuk.WEB.validators;

import org.junit.Test;
import ua.jr.raichuk.Exceptions.DataException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidatorTest {
    @Test
    public void isValidGood() throws Exception {
        assertTrue(UserValidator.isValid("Test", "23test"));
    }

    @Test
    public void isValidBadLogin() throws Exception {
        int flag = 0;
        try{
            UserValidator.isValid("Тест", "test");
        } catch (DataException e) {
            flag++;
        }
        try{
            UserValidator.isValid("1233", "test");
        } catch (DataException e) {
            flag++;
        }
        try{
            UserValidator.isValid("1Тест", "test");
        } catch (DataException e) {
            flag++;
        }
        try{
            UserValidator.isValid("1Test", "test");
        } catch (DataException e) {
            flag++;
        }
        try{
            UserValidator.isValid("*564", "test");
        } catch (DataException e) {
            flag++;
        }
        try{
            UserValidator.isValid("_Test", "test");
        } catch (DataException e) {
            flag++;
        }
        try{
            UserValidator.isValid("hj Test", "test");
        } catch (DataException e) {
            flag++;
        }
        assertTrue(flag == 7);
    }

    @Test
    public void isValidBadPassword() throws Exception {
        int flag = 0;
        try{
            UserValidator.isValid("Test", "пароль");
        } catch (DataException e) {
            flag++;
        }
        try{
            UserValidator.isValid("Test", "df/lk");
        } catch (DataException e) {
            flag++;
        }
        try{
            UserValidator.isValid("Test", "d.lk");
        } catch (DataException e) {
            flag++;
        }
        try{
            UserValidator.isValid("Test", "545fg-");
        } catch (DataException e) {
            flag++;
        }
        try{
            UserValidator.isValid("Test", "545f_d");
        } catch (DataException e) {
            flag++;
        }
        try{
            UserValidator.isValid("Test", "545 fd");
        } catch (DataException e) {
            flag++;
        }
        assertTrue(flag == 5);
    }

}