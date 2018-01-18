package ua.jr.raichuk.WEB.configaration;


import ua.jr.raichuk.WEB.commands.FactoryCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SecurityConfiguration {
    private static final SecurityConfiguration config = new SecurityConfiguration();

    public static final String ALL = "all";
    public static final String AUTH = "authorised";
    public static final String ADMIN = "administrator";

    private Map<String,String> grant = new HashMap<>();

    public static SecurityConfiguration getInstance(){
        return config;
    }

    private SecurityConfiguration() {
        grant.put(FactoryCommand.LOGIN, ALL);
        grant.put(FactoryCommand.REGISTER, ALL);
        grant.put(FactoryCommand.TRACKING, AUTH);
        grant.put(FactoryCommand.STATISTICS, AUTH);
        grant.put(FactoryCommand.SHOW_PROFILE, AUTH);
        grant.put(FactoryCommand.EDIT_PROFILE, AUTH);
        grant.put(FactoryCommand.DELETE_PROFILE, AUTH);
        grant.put(FactoryCommand.EDIT_USER, AUTH);
        grant.put(FactoryCommand.ADD_FOOD, AUTH);
        grant.put(FactoryCommand.ADD_FOOD_TODAY, AUTH);
        grant.put(FactoryCommand.LOGOUT, AUTH);
        //admin
        //food
        grant.put(FactoryCommand.ADMIN_SHOW_LIST_FOOD, ADMIN);
        grant.put(FactoryCommand.ADMIN_SHOW_NEW_FOOD_FORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_SHOW_EDIT_FOOD_FORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_INSERT_FOOD, ADMIN);
        grant.put(FactoryCommand.ADMIN_EDIT_FOOD, ADMIN);
        grant.put(FactoryCommand.ADMIN_DELETE_FOOD, ADMIN);
        //norm
        grant.put(FactoryCommand.ADMIN_SHOW_LIST_NORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_SHOW_NEW_NORM_FORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_SHOW_EDIT_NORM_FORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_INSERT_NORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_EDIT_NORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_DELETE_NORM, ADMIN);
        //waseaten
        grant.put(FactoryCommand.ADMIN_SHOW_LIST_WASEATEN, ADMIN);
        grant.put(FactoryCommand.ADMIN_SHOW_NEW_WASEATEN_FORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_SHOW_EDIT_WASEATEN_FORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_INSERT_WASEATEN, ADMIN);
        grant.put(FactoryCommand.ADMIN_EDIT_WASEATEN, ADMIN);
        grant.put(FactoryCommand.ADMIN_DELETE_WASEATEN, ADMIN);
        //link
        grant.put(FactoryCommand.ADMIN_SHOW_LIST_LINK, ADMIN);
        grant.put(FactoryCommand.ADMIN_SHOW_NEW_LINK_FORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_SHOW_EDIT_LINK_FORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_INSERT_LINK, ADMIN);
        grant.put(FactoryCommand.ADMIN_EDIT_LINK, ADMIN);
        grant.put(FactoryCommand.ADMIN_DELETE_LINK, ADMIN);
        //user
        grant.put(FactoryCommand.ADMIN_SHOW_LIST_USER, ADMIN);
        grant.put(FactoryCommand.ADMIN_SHOW_NEW_USER_FORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_SHOW_EDIT_USER_FORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_INSERT_USER, ADMIN);
        grant.put(FactoryCommand.ADMIN_EDIT_USER, ADMIN);
        grant.put(FactoryCommand.ADMIN_DELETE_USER, ADMIN);
        //profile
        grant.put(FactoryCommand.ADMIN_SHOW_LIST_PROFILE, ADMIN);
        grant.put(FactoryCommand.ADMIN_SHOW_NEW_PROFILE_FORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_SHOW_EDIT_PROFILE_FORM, ADMIN);
        grant.put(FactoryCommand.ADMIN_INSERT_PROFILE, ADMIN);
        grant.put(FactoryCommand.ADMIN_EDIT_PROFILE, ADMIN);
        grant.put(FactoryCommand.ADMIN_DELETE_PROFILE, ADMIN);
    }

    public String security(String command){
        return grant.get(command);
    }

    public Set<String> getEndPoints(){
        return grant.keySet();
    }
}
