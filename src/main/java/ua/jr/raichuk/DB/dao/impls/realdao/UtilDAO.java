package ua.jr.raichuk.DB.dao.impls.realdao;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.dao.CRUD;
import ua.jr.raichuk.DB.entities.impls.*;
import ua.jr.raichuk.Exceptions.DBException;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utils for comfort work with DB
 * Combined and applied DAO methods
 *
 * @author Jesus Raichuk
 */
public class UtilDAO{
    private static Logger LOGGER = Logger.getLogger(DAOFactory.class);

    UtilDAO(){}

    /**
     * Search user by login
     * @param login - user login
     * @param con - connection to DB
     * @return User - user with login is deed
     * @throws DBException - if connection failed or will check SQLException
     */
    public User findUserByLogin(String login, Connection con) throws DBException{
        Connection connection = con;

        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM " + new UserCRUD().getTableName() + " WHERE login=?";

        User entity = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            LOGGER.debug(preparedStatement);
            resultSet.beforeFirst();
            resultSet.next();
            entity = new UserCRUD().fillEntity(resultSet);
            preparedStatement.execute();
            if(preparedStatement != null)
                preparedStatement.close();
        } catch (Exception e){
            LOGGER.error("Connection->SQL (UtilDAO.findUserByLogin()) exception : User by login=" + login + " not found!");
            new DBException("User not found");
        }
        return entity;
    }

    /**
     * Search food by name
     * @param name - food name
     * @param con - connection to DB
     * @return Food - food was locking fo
     * @throws DBException - if connection failed or will check SQLException
     */
    public Food findFoodByName(String name, Connection con)  throws DBException{
        Connection connection = con;

        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM " + new FoodCRUD().getTableName() + " WHERE name=?";
        Food entity = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            LOGGER.debug(preparedStatement);
            resultSet.beforeFirst();
            resultSet.next();
            entity = new FoodCRUD().fillEntity(resultSet);
            preparedStatement.execute();
            if(preparedStatement != null)
                preparedStatement.close();
        } catch (Exception e){
            LOGGER.error("Connection->SQL (UtilDAO.findFoodByName()) exception : Food by name=" + name + " not found!");
            throw new DBException("Food not found");
        }
        return entity;
    }

    /**
     * Search users links by login
     * @param login - user login
     * @param connection - connection to DB
     * @return Link - user links
     * @throws DBException - if connection failed or will check SQLException
     */
    public Link findLinkByLogin(String login, Connection connection) throws DBException {
        PreparedStatement preparedStatement = null;
        String sqlFindLink = "SELECT * FROM " + new LinkCRUD().getTableName() + " WHERE " + new UserCRUD().getTableIdRowName() + "=?";

        Link entity = null;

        UtilDAO utilDAO = DAOFactory.getInstance().getUtilDAO();
        User user = utilDAO.findUserByLogin(login, connection);
        System.out.println(user);

        try {
            preparedStatement = connection.prepareStatement(sqlFindLink);

            preparedStatement.setInt(1, user.getIdU());
            ResultSet resultSet = preparedStatement.executeQuery();
            LOGGER.debug(preparedStatement);
            resultSet.beforeFirst();
            resultSet.next();
            entity = new LinkCRUD().fillEntity(resultSet);
            preparedStatement.execute();


            if(preparedStatement != null){
                preparedStatement.close();
            }
        } catch (Exception e) {
            LOGGER.error("Connection->SQL (UtilDAO.findLinkByLogin()) exception : Link by login=" + login + " not found!");
            new DBException("Link not found");
        }

        return entity;
    }

    /**
     * Search all food were eaten users by user login
     * @param login - user login
     * @param connection - connection to DB
     * @return - List WasEaten - list foods were eaten the user
     * @throws DBException - if connection failed or will check SQLException
     */
    public List<WasEaten> findWasEatenByLogin(String login, Connection connection) throws DBException {
        PreparedStatement preparedStatement = null;
        String sqlFindWasEaten = "SELECT * FROM " + new WasEatenCRUD().getTableName() + " WHERE " + new ProfileCRUD().getTableIdRowName() + "=?";

        List<WasEaten> entities = new ArrayList<WasEaten>();
        try{
            Link link = DAOFactory.getInstance().getUtilDAO().findLinkByLogin(login, connection);

            preparedStatement = connection.prepareStatement(sqlFindWasEaten);
            preparedStatement.setInt(1, link.getIdP());
            LOGGER.debug(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();


            while(resultSet.next()) {
                WasEaten entity = new WasEatenCRUD().fillEntity(resultSet);
                entities.add(entity);
            }
            preparedStatement.execute();

            if(preparedStatement != null){
                preparedStatement.close();
            }
        } catch (Exception e2) {
            LOGGER.error("Connection->SQL (UtilDAO.findWasEatenByLogin()) exception : WasEaten by login=" + login + " not found!");
            throw new DBException("WasEaten not found");
        }
        return entities;
    }

    /**
     * Search profile data (Profile) by user login
     * @param login - user login
     * @param connection - connection to DB
     * @return Profile - profile information
     * @throws DBException - if connection failed or will check SQLException
     */
    public Profile findProfileByLogin(String login, Connection connection) throws DBException {
        Profile entity = null;
        Link link = DAOFactory.getInstance().getUtilDAO().findLinkByLogin(login, connection);
        entity = (Profile) DAOFactory.getInstance().getCRUD(new Profile()).findById(link.getIdP(), connection);
        return entity;
    }

    /**
     * Find WasEaten by profile id
     * @param pId - profile id
     * @param connection - connection to DB
     * @return List WasEaten - list date and ids food were eaten
     * @throws DBException - if connection failed or will check SQLException
     */
    public List<WasEaten> findWasEatenByProfileId(int pId, Connection connection) throws DBException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM " + new WasEatenCRUD().getTableName() + " WHERE " + new ProfileCRUD().getTableIdRowName() + "=?";

        List<WasEaten> wsL = new ArrayList<WasEaten>();
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pId);
            LOGGER.debug(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                WasEaten wasEaten = new WasEaten();
                wasEaten.setIdWE(resultSet.getInt("idWE"));
                wasEaten.setIdP(resultSet.getInt("idP"));
                wasEaten.setIdF(resultSet.getInt("idF"));
                wasEaten.setDateWE(resultSet.getDate("dateWE"));
                wsL.add(wasEaten);
            }

            if(preparedStatement != null){
                preparedStatement.close();
            }
        } catch (Exception e2) {
            LOGGER.error("Connection->SQL (UtilDAO.findWasEatenByProfileId()) exception : WasEaten by Profile Id=" + pId + " not found!");
            throw new DBException("WasEaten not found");
        }
        return wsL;
    }

    /**
     * Add new Profile to Site (new User, new Norm, new Profile)
     * @param user - new User (user data)
     * @param profile - new Profile (profile data)
     * @param norm - new Norm (daily norm)
     * @param connection - connection to DB
     * @throws DBException - if connection failed or will check SQLException
     */
    public void addProfile(User user, Profile profile, Norm norm, Connection connection) throws DBException{
        DAOFactory.getInstance().getCRUD(user).add(user, connection);
        DAOFactory.getInstance().getCRUD(profile).add(profile, connection);
        DAOFactory.getInstance().getCRUD(norm).add(norm, connection);
    }

    /**
     * Update user data (login and password)
     * @param login - old user login
     * @param newLogin - new user login
     * @param newPassword - new user password
     * @param connection - connection to DB
     * @throws DBException - if connection failed or will check SQLException
     */
    public void editUser(String login, String newLogin, String newPassword, Connection connection) throws DBException {
        User user = DAOFactory.getInstance().getUtilDAO().findUserByLogin(login, connection);

        user.setLogin(newLogin);
        user.setPassword(newPassword);

        DAOFactory.getInstance().getCRUD(user).update(user, connection);
    }

    /**
     * Delete Profile from site (Delete all information about user Profile):
     * User data
     * Profile data
     * Norm data
     * WasEaten data
     * @param login - user login
     * @param connection - connection to DB
     * @throws DBException - if connection failed or will check SQLException
     */
    public void deleteProfile(String login, Connection connection) throws DBException {
        Link link = DAOFactory.getInstance().getUtilDAO().findLinkByLogin(login, connection);
        List<WasEaten> listWE = DAOFactory.getInstance().getUtilDAO().findWasEatenByLogin(login, connection);

        //Delete
        //Link
        CRUD crud = DAOFactory.getInstance().getCRUD(link);
        crud.delete(link.getIdL(), connection);
        //WasEaten
        crud = DAOFactory.getInstance().getCRUD(new WasEaten());
        for (int i = 0; i < listWE.size(); i++) {
            crud.delete(listWE.get(i).getIdWE(), connection);
        }
        //Norm
        crud = DAOFactory.getInstance().getCRUD(new Norm());
        crud.delete(link.getIdN(), connection);
        //Profile
        crud = DAOFactory.getInstance().getCRUD(new Profile());
        crud.delete(link.getIdP(), connection);
        //User
        crud = DAOFactory.getInstance().getCRUD(new User());
        crud.delete(link.getIdU(), connection);
    }

    /**
     * Delete each WasEaten item by food id
     * @param idF - food id
     * @param connection - connection to DB
     * @throws DBException - if connection failed or will check SQLException
     */
    public void deleteWasEatenByFoodId(int idF, Connection connection) throws DBException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM " + new WasEatenCRUD().getTableName() + " WHERE " + new FoodCRUD().getTableIdRowName() + "=?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (Integer)idF);
            LOGGER.debug(preparedStatement);
            preparedStatement.executeUpdate();
            if(preparedStatement != null)
                preparedStatement.close();
        } catch (Exception e){
            LOGGER.error("Connection->SQL (UtilDAO.deleteWasEatenByFoodId()) exception : WasEaten by Food Id=" + idF + " not found!");
            throw new DBException("Not deleted");
        }
    }

}
