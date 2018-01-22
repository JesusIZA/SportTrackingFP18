package ua.jr.raichuk.WEB.validators;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.DB.transactions.Transaction;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jesus Raichuk
 */
public abstract class EnterDataValidator {
    private static Logger LOGGER = Logger.getLogger(EnterDataValidator.class);
    //patterns
    //login
    public static final String LOGIN_PATTERN = "[A-Za-z]+[A-Za-z0-9]*";
    //password
    public static final String PASSWORD_PATTERN = "[A-za-z0-9]+";
    //name and surname
    public static final String NAME_PATTERN = "[A-ZА-Яa-zа-я]+";
    //food name
    public static final String FOOD_NAME_PATTERN = "([A-ZА-Яa-zа-я0-9_]+[\\s]?)+";
    //sex
    public static final String SEX_PATTERN = "male|female";

    public static boolean isValidLogin(String word) throws DataException {
        return validTextField(word, LOGIN_PATTERN, 56);
    }
    public static boolean isValidSex(String word) throws DataException {
        return validTextField(word, SEX_PATTERN, 10);
    }
    public static boolean isValidPassword(String word) throws DataException {
        return validTextField(word, PASSWORD_PATTERN, 56);
    }
    public static boolean isValidNameOrSurname(String word) throws DataException {
        return validTextField(word, NAME_PATTERN, 56);
    }
    public static boolean isValidFoodName(String word) throws DataException {
        return validTextField(word, FOOD_NAME_PATTERN, 250);
    }
    public static boolean isValidHeightOrWeight(double real) {
        return validRealValue(real, 1.0, 999.0);
    }
    public static boolean isValidActiveTime(int number) {
        return validNumberValue(number, 1, 99);
    }
    public static boolean isValidCaloriesOrProteinsOrFatsOrCarbohydrates(double real) {
        return validRealValue(real, 0.0, 999.0);
    }
    public static boolean isValidNormCalories(int number) {
        return validNumberValue(number, 0, 10000);
    }
    public static boolean isValidNormProteins(int number) {
        return validNumberValue(number, 0, 1000);
    }
    public static boolean isFreeLogin(String word) throws TransactionException{
        User user = null;

        Connection connection = Transaction.startTransaction();
        try {
            user = DAOFactory.getInstance().getUtilDAO().findUserByLogin(word, connection);

            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.DAO (EnterDataValidator.isFreeLogin()) exception : UtilDAO.findUserByLogin get User error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }

        if(Objects.isNull(user))
            return true;
        return false;
    }
    public static boolean isValidDate(Date date) {
        Date minDate = Date.valueOf("1900-01-01");
        Date maxDate = Date.valueOf("1900-01-01");
        maxDate.getTime();
        maxDate.setTime(new java.util.Date().getTime());

        if(date.getTime() <= maxDate.getTime() && date.getTime() >= minDate.getTime())
            return true;
        return false;
    }
    public static boolean isValidDateRange(Date start, Date end) {
        if(start.getTime() > end.getTime()){
            Date temp = start;
            start = end;
            end = temp;
        }
        if(end.getTime() - start.getTime() > 3156000000L)
            return false;
        return true;
    }
    public static boolean isIdExisting(int id, Entity entity) throws TransactionException {
        Connection connection = Transaction.startTransaction();
        List<Integer> ids = new ArrayList<Integer>();
        try {
            ids = DAOFactory.getInstance().getCRUD(entity).getAllIds(connection);
            for (int i = 0; i < ids.size(); i++) {
                if(id == ids.get(i))
                    return true;
            }
            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.CRUD (EnterDataValidator.isIdExisting()) exception : CRUD.getAllIds get Ids error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        return false;
    }

    private static boolean validTextField(String word, String pattern, int length) throws DataException {
        try{
                if(!Objects.equals(word, "") && !Objects.isNull(word) && word.length() < length) {
                Pattern patt = Pattern.compile(pattern);
                Matcher matcher = patt.matcher(word);

                matcher.find();
                String found = matcher.group();

                if(!Objects.equals(found, "") && !Objects.isNull(found) && Objects.equals(found, word))
                    return true;
            }
        } catch (Exception e) {
            LOGGER.error("DataValidator.Text (EnterDataValidator.validTextField()) exception : Text Data is incorrect, error!");
            throw new DataException("Text data is illegal");
        }
        return false;
    }
    private static boolean validRealValue(double val, double min, double max) {
        if(val >= min && val <= max)
            return true;
        return false;
    }
    private static boolean validNumberValue(int val, int min, int max) {
        if(val >= min && val <= max)
            return true;
        return false;
    }


}
