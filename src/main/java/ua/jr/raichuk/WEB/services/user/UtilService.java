package ua.jr.raichuk.WEB.services.user;

import ua.jr.raichuk.DB.entities.impls.Profile;

import java.sql.Date;

/**
 * @author Jesus Raichuk
 */
public abstract class UtilService{
    public static int getNormCalories(Profile profile){
        double ret = 0.0;

        Date today = Date.valueOf("1900-01-01");
        today.getTime();
        today.setTime(new java.util.Date().getTime());
        System.out.println(profile);

        long age = (today.getTime() - profile.getBirthday().getTime()) / 31560000000L;

        if(profile.getSex().equals("male")){
            ret = 88.36 + (13.4 * profile.getWeight()) + (4.8 * profile.getHeight()) + (5.7 * age);
        } else {
            ret = 447.6 + (9.2 * profile.getWeight()) + (3.1 * profile.getHeight()) + (4.3 * age);
        }

        ret *= ((profile.getActiveTime() / 100) + 1);

        return (int)ret;
    }
    public static int getNormProteins(Profile profile){
        double ret = 0.0;
        if(profile.getSex().equals("male")){
            ret = profile.getWeight() * 2.0;
        } else {
            ret = profile.getWeight() * 1.5;
        }
        return (int)ret;
    }
}
