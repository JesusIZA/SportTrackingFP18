package ua.jr.raichuk.DB.dao.impls.realdao;


import ua.jr.raichuk.DB.dao.CRUD;
import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.transactions.Transaction;

/**
 * DAO Factory class
 * Factory, Singleton
 *
 * @author Jesus Raichuk
 */
public class DAOFactory {
    private static DAOFactory instance = new DAOFactory();

    private static UserCRUD ui = new UserCRUD();
    private static ProfileCRUD pi = new ProfileCRUD();
    private static FoodCRUD fi = new FoodCRUD();
    private static WasEatenCRUD wi = new WasEatenCRUD();
    private static NormCRUD ni = new NormCRUD();
    private static LinkCRUD li = new LinkCRUD();
    private static UtilDAO ud = new UtilDAO();

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public CRUD getCRUD(Entity entity) {
        switch (entity.getClassName()){
            case "User":
                return ui;
            case "Food":
                return fi;
            case "Profile":
                return pi;
            case "WasEaten":
                return wi;
            case "Norm":
                return ni;
            case "Link":
                return li;
            default:
                return null;
        }
    }

    public UtilDAO getUtilDAO() {
        return ud;
    }
}
