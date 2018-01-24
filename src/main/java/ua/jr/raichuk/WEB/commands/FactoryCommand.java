package ua.jr.raichuk.WEB.commands;

import ua.jr.raichuk.WEB.commands.admindo.food.*;
import ua.jr.raichuk.WEB.commands.admindo.link.*;
import ua.jr.raichuk.WEB.commands.admindo.norm.*;
import ua.jr.raichuk.WEB.commands.admindo.profile.*;
import ua.jr.raichuk.WEB.commands.admindo.user.*;
import ua.jr.raichuk.WEB.commands.admindo.waseaten.*;
import ua.jr.raichuk.WEB.commands.userdo.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory return ready command for user queries by the site
 *
 * @author Jesus Raichuk
 */
public class FactoryCommand {
    private static final FactoryCommand factoryCommand = new FactoryCommand();

    //users
    public static final String LOGIN = "login";
    public static final String REGISTER = "registration";
    public static final String TRACKING = "tracking";
    public static final String STATISTICS = "statistics";
    public static final String SHOW_PROFILE = "showprofile";
    public static final String EDIT_PROFILE = "editprofile";
    public static final String DELETE_PROFILE = "deleteprofile";
    public static final String EDIT_USER = "edituser";
    public static final String ADD_FOOD = "addfood";
    public static final String ADD_FOOD_TODAY = "addfoodtoday";
    public static final String LOGOUT = "logout";

    //admin
    //food
    public static final String ADMIN_SHOW_LIST_FOOD = "adminshowlistfood";
    public static final String ADMIN_SHOW_EDIT_FOOD_FORM = "adminshoweditfoodform";
    public static final String ADMIN_SHOW_NEW_FOOD_FORM = "adminshownewfoodform";
    public static final String ADMIN_INSERT_FOOD = "admininsertfood";
    public static final String ADMIN_EDIT_FOOD = "admineditfood";
    public static final String ADMIN_DELETE_FOOD = "admindeletefood";
    //norm
    public static final String ADMIN_SHOW_LIST_NORM = "adminshowlistnorm";
    public static final String ADMIN_SHOW_EDIT_NORM_FORM = "adminshoweditnormform";
    public static final String ADMIN_SHOW_NEW_NORM_FORM = "adminshownewnormform";
    public static final String ADMIN_INSERT_NORM = "admininsertnorm";
    public static final String ADMIN_EDIT_NORM = "admineditnorm";
    public static final String ADMIN_DELETE_NORM = "admindeletenorm";
    //waseaten
    public static final String ADMIN_SHOW_LIST_WASEATEN = "adminshowlistwaseaten";
    public static final String ADMIN_SHOW_EDIT_WASEATEN_FORM = "adminshoweditwaseatenform";
    public static final String ADMIN_SHOW_NEW_WASEATEN_FORM = "adminshownewwaseatenform";
    public static final String ADMIN_INSERT_WASEATEN = "admininsertwaseaten";
    public static final String ADMIN_EDIT_WASEATEN = "admineditwaseaten";
    public static final String ADMIN_DELETE_WASEATEN = "admindeletewaseaten";
    //link
    public static final String ADMIN_SHOW_LIST_LINK = "adminshowlistlink";
    public static final String ADMIN_SHOW_EDIT_LINK_FORM = "adminshoweditlinkform";
    public static final String ADMIN_SHOW_NEW_LINK_FORM = "adminshownewlinkform";
    public static final String ADMIN_INSERT_LINK = "admininsertlink";
    public static final String ADMIN_EDIT_LINK = "admineditlink";
    public static final String ADMIN_DELETE_LINK = "admindeletelink";
    //user
    public static final String ADMIN_SHOW_LIST_USER = "adminshowlistuser";
    public static final String ADMIN_SHOW_EDIT_USER_FORM = "adminshowedituserform";
    public static final String ADMIN_SHOW_NEW_USER_FORM = "adminshownewuserform";
    public static final String ADMIN_INSERT_USER = "admininsertuser";
    public static final String ADMIN_EDIT_USER = "adminedituser";
    public static final String ADMIN_DELETE_USER = "admindeleteuser";
    //profile
    public static final String ADMIN_SHOW_LIST_PROFILE = "adminshowlistprofile";
    public static final String ADMIN_SHOW_EDIT_PROFILE_FORM = "adminshoweditprofileform";
    public static final String ADMIN_SHOW_NEW_PROFILE_FORM = "adminshownewprofileform";
    public static final String ADMIN_INSERT_PROFILE = "admininsertprofile";
    public static final String ADMIN_EDIT_PROFILE = "admineditprofile";
    public static final String ADMIN_DELETE_PROFILE = "admindeleteprofile";

    /**
     * Map all commands
     */
    private Map<String,Command> commandMap = new HashMap<>();

    private FactoryCommand() {
        //users
        commandMap.put(LOGIN, new LoginCommand());
        commandMap.put(REGISTER, new RegistrationCommand());
        commandMap.put(TRACKING, new TrackingCommand());
        commandMap.put(STATISTICS, new StatisticsCommand());
        commandMap.put(SHOW_PROFILE, new ShowProfileCommand());
        commandMap.put(EDIT_PROFILE, new EditProfileCommand());
        commandMap.put(DELETE_PROFILE, new DeleteAllProfileCommand());
        commandMap.put(EDIT_USER, new EditUserCommand());
        commandMap.put(ADD_FOOD, new AddFoodCommand());
        commandMap.put(ADD_FOOD_TODAY, new AddFoodTodayCommand());
        commandMap.put(LOGOUT, new LogoutCommand());
        //admin
        //food
        commandMap.put(ADMIN_SHOW_LIST_FOOD, new ShowListFoodCommand());
        commandMap.put(ADMIN_SHOW_EDIT_FOOD_FORM, new ShowEditFoodFormCommand());
        commandMap.put(ADMIN_SHOW_NEW_FOOD_FORM, new ShowNewFoodFormCommand());
        commandMap.put(ADMIN_INSERT_FOOD, new InsertFoodCommand());
        commandMap.put(ADMIN_EDIT_FOOD, new UpdateFoodCommand());
        commandMap.put(ADMIN_DELETE_FOOD, new DeleteFoodCommand());
        //norm
        commandMap.put(ADMIN_SHOW_LIST_NORM, new ShowListNormCommand());
        commandMap.put(ADMIN_SHOW_EDIT_NORM_FORM, new ShowEditNormFormCommand());
        commandMap.put(ADMIN_SHOW_NEW_NORM_FORM, new ShowNewNormFormCommand());
        commandMap.put(ADMIN_INSERT_NORM, new InsertNormCommand());
        commandMap.put(ADMIN_EDIT_NORM, new UpdateNormCommand());
        commandMap.put(ADMIN_DELETE_NORM, new DeleteNormCommand());
        //waseaten
        commandMap.put(ADMIN_SHOW_LIST_WASEATEN, new ShowListWasEatenCommand());
        commandMap.put(ADMIN_SHOW_EDIT_WASEATEN_FORM, new ShowEditWasEatenFormCommand());
        commandMap.put(ADMIN_SHOW_NEW_WASEATEN_FORM, new ShowNewWasEatenFormCommand());
        commandMap.put(ADMIN_INSERT_WASEATEN, new InsertWasEatenCommand());
        commandMap.put(ADMIN_EDIT_WASEATEN, new UpdateWasEatenCommand());
        commandMap.put(ADMIN_DELETE_WASEATEN, new DeleteWasEatenCommand());
        //link
        commandMap.put(ADMIN_SHOW_LIST_LINK, new ShowListLinkCommand());
        commandMap.put(ADMIN_SHOW_EDIT_LINK_FORM, new ShowEditLinkFormCommand());
        commandMap.put(ADMIN_SHOW_NEW_LINK_FORM, new ShowNewLinkFormCommand());
        commandMap.put(ADMIN_INSERT_LINK, new InsertLinkCommand());
        commandMap.put(ADMIN_EDIT_LINK, new UpdateLinkCommand());
        commandMap.put(ADMIN_DELETE_LINK, new DeleteLinkCommand());
        //user
        commandMap.put(ADMIN_SHOW_LIST_USER, new ShowListUserCommand());
        commandMap.put(ADMIN_SHOW_EDIT_USER_FORM, new ShowEditUserFormCommand());
        commandMap.put(ADMIN_SHOW_NEW_USER_FORM, new ShowNewUserFormCommand());
        commandMap.put(ADMIN_INSERT_USER, new InsertUserCommand());
        commandMap.put(ADMIN_EDIT_USER, new UpdateUserCommand());
        commandMap.put(ADMIN_DELETE_USER, new DeleteUserCommand());
        //profile
        commandMap.put(ADMIN_SHOW_LIST_PROFILE, new ShowListProfileCommand());
        commandMap.put(ADMIN_SHOW_EDIT_PROFILE_FORM, new ShowEditProfileFormCommand());
        commandMap.put(ADMIN_SHOW_NEW_PROFILE_FORM, new ShowNewProfileFormCommand());
        commandMap.put(ADMIN_INSERT_PROFILE, new InsertProfileCommand());
        commandMap.put(ADMIN_EDIT_PROFILE, new UpdateProfileCommand());
        commandMap.put(ADMIN_DELETE_PROFILE, new DeleteProfileCommand());
    }

    public static FactoryCommand getInstance(){
        return factoryCommand;
    }

    public Command getCommand(String command){
        return commandMap.get(command);
    }

}
