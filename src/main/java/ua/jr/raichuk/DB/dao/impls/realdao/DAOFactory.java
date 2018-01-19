package ua.jr.raichuk.DB.dao.impls.realdao;


import org.apache.log4j.Logger;
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
    private static Logger LOGGER = Logger.getLogger(DAOFactory.class);
    private static DAOFactory instance = new DAOFactory();

    private static UserCRUD ui = new UserCRUD();
    private static ProfileCRUD pi = new ProfileCRUD();
    private static FoodCRUD fi = new FoodCRUD();
    private static WasEatenCRUD wi = new WasEatenCRUD();
    private static NormCRUD ni = new NormCRUD();
    private static LinkCRUD li = new LinkCRUD();
    private static UtilDAO ud = new UtilDAO();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public CRUD getCRUD(Entity entity) {
        switch (entity.getClassName()){
            case "User": {
                LOGGER.debug("CRUD for User was got.");
                return ui;
            }
            case "Food": {
                LOGGER.debug("CRUD for Food was got.");
                return fi;
            }
            case "Profile": {
                LOGGER.debug("CRUD for Profile was got.");
                return pi;
            }
            case "WasEaten": {
                LOGGER.debug("CRUD for WasEaten was got.");
                return wi;
            }
            case "Norm": {
                LOGGER.debug("CRUD for Norm was got.");
                return ni;
            }
            case "Link": {
                LOGGER.debug("CRUD for Link was got.");
                return li;
            }
            default: {
                LOGGER.debug("Was got CRUD-null.");
                return null;
            }
        }
    }

    public UtilDAO getUtilDAO() {
        return ud;
    }
}
