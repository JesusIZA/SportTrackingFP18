package ua.jr.raichuk.WEB.services.admin;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.DB.entities.impls.Norm;
import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.DB.transactions.Transaction;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.services.user.UtilService;

import java.sql.Connection;

/**
 * @author Jesus Raichuk
 */
public class ProfileService extends AdminService<Profile> {
    private static Logger LOGGER = Logger.getLogger(ProfileService.class);

    ProfileService(){}

    /**
     * Realized right editing profile (edit norm if it is needed also)
     * @param login - user login
     * @param profile - profile need update
     * @throws TransactionException
     */
    public void editProfile(String login, Profile profile) throws TransactionException{
        Connection connection = Transaction.startTransaction();
        try {
            Link link = DAOFactory.getInstance().getUtilDAO().findLinkByLogin(login, connection);
            Profile prof = (Profile)DAOFactory.getInstance().getCRUD(profile).findById(link.getIdP(), connection);
            System.out.println(prof);
            prof.setName(profile.getName());
            prof.setSurname(profile.getSurname());
            prof.setSex(profile.getSex());
            prof.setBirthday(profile.getBirthday());
            prof.setHeight(profile.getHeight());
            prof.setWeight(profile.getWeight());
            prof.setActiveTime(profile.getActiveTime());
            DAOFactory.getInstance().getCRUD(prof).update(prof, connection);

            Norm norm = (Norm)DAOFactory.getInstance().getCRUD(new Norm()).findById(link.getIdN(), connection);
            norm.setCalories(UtilService.getNormCalories(prof));
            norm.setProteins(UtilService.getNormProteins(prof));
            DAOFactory.getInstance().getCRUD(norm).update(norm, connection);

            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.DAO (ProfileService.editProfile()) exception : CRUD,UtilDAO for Profile find and edit error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
    }

    /**
     * Get profile information by login
     * @param login - user login
     * @return - Profile - profile information
     * @throws TransactionException - if has some problem with DB
     */
    public Profile getProfile(String login) throws TransactionException{
        Profile profile = null;
        Connection connection = Transaction.startTransaction();
        try {
            Link link = DAOFactory.getInstance().getUtilDAO().findLinkByLogin(login, connection);
            profile = (Profile)DAOFactory.getInstance().getCRUD(new Profile()).findById(link.getIdP(), connection);

            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.DAO,CRUD (ProfileService.getProfile()) exception : UtilDAO,CRUD for Profile find error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        return profile;
    }

    /**
     * Delete all profile information
     * @param login - user login
     * @throws TransactionException - if has some problem with DB
     */
    public void deleteProfile(String login) throws TransactionException {Profile profile = null;
        Connection connection = Transaction.startTransaction();
        try {
            DAOFactory.getInstance().getUtilDAO().deleteProfile(login, connection);

            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.DAO (ProfileService.deleteProfile()) exception : UtilDAO.deleteProfile for Profile edit error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
    }

    @Override
    public Entity getEmptyClass() {
        return new Profile();
    }
}
