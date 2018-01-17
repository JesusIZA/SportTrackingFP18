package ua.jr.raichuk.Helpers.testmain;

import ua.jr.raichuk.Helpers.parsers.ParseData;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jesus Raichuk
 */
public class Main {
    public static void main(String[] args) {
        Date today = Date.valueOf("2018-01-01");
        today.getTime();
        today.setTime(new java.util.Date().getTime());
        Date eagg = Date.valueOf("1996-05-12");

        /*long age = (today.getTime() - eagg.getTime()) / 31560000000L;*/

        double ret = 88.36 + (13.4 * 82) + (4.8 * 184) + (5.7 * 21);

        ret *= 1.2;

        System.out.println(ret);

        Date today2 = Date.valueOf("2018-01-15");

        /*System.out.println(today.getTime());
        System.out.println(today2.getTime());
        System.out.println(today.getYear() == today2.getYear());
        System.out.println(today.getMonth() == today2.getMonth());
        System.out.println(today.getDay() == today2.getDay());*/



         String FOOD_NAME_PATTERN = "([a-zA-Z_а-яА-Я0-9]+[\\s]?)+";
         String SEX_PATTERN = "male|female";

        Pattern patt = Pattern.compile(SEX_PATTERN);
        Matcher matcher = patt.matcher("female ");

        matcher.find();
        String found = matcher.group();

        System.out.println(found.equals("male") || found.equals("female"));

        Pattern patt1 = Pattern.compile(FOOD_NAME_PATTERN);
        Matcher matcher1 = patt1.matcher("neme of fuu 34 dfd  %sd ");

        matcher1.find();
        String found1 = matcher1.group();
        System.out.println(found1);
    }
}
