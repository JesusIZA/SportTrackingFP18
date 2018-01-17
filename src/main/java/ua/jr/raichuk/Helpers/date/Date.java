package ua.jr.raichuk.Helpers.date;

/**
 * @author Jesus Raichuk
 */
public abstract class Date {
    public static final java.sql.Date getNowSQLDate(){
        java.sql.Date date = java.sql.Date.valueOf("1900-01-01");

        date.getTime();
        date.setTime(new java.util.Date().getTime());

        return date;
    }
}
