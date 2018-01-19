package ua.jr.raichuk.WEB.services.admin;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.Entity;

/**
 * @author Jesus Raichuk
 */
public abstract class AdminServiceFactory {
    private static Logger LOGGER = Logger.getLogger(AdminServiceFactory.class);

    private static FoodService foodService = new FoodService();
    private static WasEatenService wasEatenService = new WasEatenService();
    private static NormService normService = new NormService();
    private static UserService userService = new UserService();
    private static ProfileService profileService = new ProfileService();
    private static LinkService linkService = new LinkService();

    public static AdminService getAdminService(Entity entity) {
        switch (entity.getClassName()){
            case "User": {
                LOGGER.debug("Service for User was got.");
                return userService;
            }
            case "Food": {
                LOGGER.debug("Service for Food was got.");
                return foodService;
            }
            case "Link": {
                LOGGER.debug("Service for Link was got.");
                return linkService;
            }
            case "Profile": {
                LOGGER.debug("Service for Profile was got.");
                return profileService;
            }
            case "WasEaten": {
                LOGGER.debug("Service for WasEaten was got.");
                return wasEatenService;
            }
            case "Norm": {
                LOGGER.debug("Service for Norm was got.");
                return normService;
            }
            default: {
                LOGGER.debug("Was get Service-null.");
                return null;
            }
        }
    }
}
