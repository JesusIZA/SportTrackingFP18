package ua.jr.raichuk.WEB.validators;

import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.DB.transactions.Transaction;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.Connection;
import java.sql.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jesus Raichuk
 */
public abstract class EnterDataValidator {
    public static final String LOGIN_PATTERN = "[A-Za-z]+[A-Za-z0-9]*";
    public static final String PASSWORD_PATTERN = "[A-za-z0-9]+";
    public static final String NAME_PATTERN = "[A-ZА-Яa-zа-я_]+";
    public static final String FOOD_NAME_PATTERN = "([A-ZА-Яa-zа-я0-9_]+[\\s]?)+";
    public static final String SEX_PATTERN = "male|female";

    public static boolean isValidLogin(String word) {
        return validTextField(word, LOGIN_PATTERN, 56);
    }
    public static boolean isValidSex(String word) {
        return validTextField(word, SEX_PATTERN, 10);
    }
    public static boolean isValidPassword(String word) {
        return validTextField(word, PASSWORD_PATTERN, 56);
    }
    public static boolean isValidNameOrSurname(String word) {
        return validTextField(word, NAME_PATTERN, 56);
    }
    public static boolean isValidFoodName(String word) {
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
    public static boolean isValidCaloriesOrProteins(int number) {
        return validNumberValue(number, 0, 10000);
    }
    public static boolean isFreeLogin(String word) throws TransactionException{
        User user = null;

        Connection connection = Transaction.startTransaction();
        try {
            user = DAOFactory.getInstance().getUtilDAO().findUserByLogin(word, connection);

            Transaction.commit(connection);
        } catch (Exception e) {
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
        return true;
    }

    private static boolean validTextField(String word, String pattern, int length) {
        if(!Objects.equals(word, "") && !Objects.isNull(word) && word.length() < length) {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(word);

            matcher.find();
            String found = matcher.group();

            System.out.println(found + " " + word);

            if(!Objects.equals(found, "") && !Objects.isNull(found) && Objects.equals(found, word))
                return true;
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
