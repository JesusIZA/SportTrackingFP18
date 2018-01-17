package ua.jr.raichuk.WEB.services.admin;

import ua.jr.raichuk.DB.entities.Entity;

/**
 * @author Jesus Raichuk
 */
public abstract class AdminServiceFactory {
    private static FoodService foodService = new FoodService();
    private static WasEatenService wasEatenService = new WasEatenService();
    private static NormService normService = new NormService();
    private static UserService userService = new UserService();
    private static ProfileService profileService = new ProfileService();

    public static AdminService getAdminService(Entity entity) {
        switch (entity.getClassName()){
            case "User":
                return userService;
            case "Food":
                return foodService;
            case "Profile":
                return profileService;
            case "WasEaten":
                return wasEatenService;
            case "Norm":
                return normService;
            default:
                return null;
        }
    }
}
