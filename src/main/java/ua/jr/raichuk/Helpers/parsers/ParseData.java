package ua.jr.raichuk.Helpers.parsers;

import ua.jr.raichuk.Helpers.arrays.PrintArrays;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jesus Raichuk
 */
public abstract class ParseData {
    public static final Date parseToSQLDate(final String date){
        Pattern urlPattern = Pattern.compile("[0-9]+");
        Matcher matcher = urlPattern.matcher(date);

        StringBuilder builder = new StringBuilder();
        while (matcher.find())
            builder.append(matcher.group() + " ");

        String[] numbers = builder.toString().split("\\s");

        PrintArrays.printArrayByOneRow(numbers);

        Integer day = Integer.valueOf(numbers[0]) - 1900;
        Integer month = Integer.valueOf(numbers[1]) - 1;
        Integer year = Integer.valueOf(numbers[2]);

        Date retDate = new Date(day, month, year);
        return retDate;
    }
}
