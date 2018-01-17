package ua.jr.raichuk.DB.dao.impls.realdao;

import ua.jr.raichuk.DB.dao.CRUD;
import ua.jr.raichuk.DB.entities.impls.*;
import ua.jr.raichuk.Exceptions.DBException;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jesus Raichuk
 */
public class UtilDAO{

    UtilDAO(){}

    public User findUserByLogin(String login, Connection con) throws DBException{
        Connection connection = con;

        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM " + new UserCRUD().getTableName() + " WHERE login=?";
        System.out.println(sql + " " + login);

        User entity = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            resultSet.next();
            entity = new UserCRUD().fillEntity(resultSet);
            preparedStatement.execute();
        } catch (Exception e){
            new DBException("User not found");
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return entity;
    }

    public Food findFoodByName(String name, Connection con)  throws DBException{
        Connection connection = con;

        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM " + new FoodCRUD().getTableName() + " WHERE name=?";
        System.out.println(sql);

        Food entity = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            resultSet.next();
            entity = new FoodCRUD().fillEntity(resultSet);
            preparedStatement.execute();
        } catch (Exception e){
            throw new DBException("Food not found");
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return entity;
    }

    public Link findLinkByLogin(String login, Connection connection) throws DBException {
        PreparedStatement preparedStatement = null;
        String sqlFindLink = "SELECT * FROM " + new LinkCRUD().getTableName() + " WHERE " + new UserCRUD().getTableIdRowName() + "=?";

        System.out.println(sqlFindLink);

        Link entity = null;

        UtilDAO utilDAO = DAOFactory.getInstance().getUtilDAO();
        User user = utilDAO.findUserByLogin(login, connection);
        System.out.println(user);

        try {
            preparedStatement = connection.prepareStatement(sqlFindLink);

            preparedStatement.setInt(1, user.getIdU());
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.beforeFirst();
            resultSet.next();
            entity = new LinkCRUD().fillEntity(resultSet);
            preparedStatement.execute();


            if(preparedStatement != null){
                preparedStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new DBException("Link not found");
        }

        return entity;
    }

    public List<WasEaten> findWasEatenByLogin(String login, Connection connection) throws DBException {
        PreparedStatement preparedStatement = null;
        String sqlFindWasEaten = "SELECT * FROM " + new WasEatenCRUD().getTableName() + " WHERE " + new ProfileCRUD().getTableIdRowName() + "=?";

        System.out.println(sqlFindWasEaten);

        List<WasEaten> entities = new ArrayList<WasEaten>();
        try{
            Link link = DAOFactory.getInstance().getUtilDAO().findLinkByLogin(login, connection);

            preparedStatement = connection.prepareStatement(sqlFindWasEaten);
            preparedStatement.setInt(1, link.getIdP());
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
            e2.printStackTrace();
            throw new DBException("WasEaten not found");
        }
        return entities;
    }

    public Profile findProfileByLogin(String login, Connection connection) throws DBException {

        Profile entity = null;

        Link link = DAOFactory.getInstance().getUtilDAO().findLinkByLogin(login, connection);
        System.out.println(link);

        entity = (Profile) DAOFactory.getInstance().getCRUD(new Profile()).findById(link.getIdP(), connection);

        System.out.println(entity);
        return entity;
    }

    public List<WasEaten> findWasEatenByProfileId(int pId, Connection connection) throws DBException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM " + new WasEatenCRUD().getTableName() + " WHERE " + new ProfileCRUD().getTableIdRowName() + "=?";

        System.out.println(sql);

        List<WasEaten> wsL = new ArrayList<WasEaten>();
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pId);
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
            throw new DBException("WasEaten not found");
        }
        return wsL;
    }

    public void addProfile(User user, Profile profile, Norm norm, Connection connection) throws DBException{
        DAOFactory.getInstance().getCRUD(user).add(user, connection);
        DAOFactory.getInstance().getCRUD(profile).add(profile, connection);
        DAOFactory.getInstance().getCRUD(norm).add(norm, connection);
    }

    public void editUser(String login, String newLogin, String newPassword, Connection connection) throws DBException {
        User user = DAOFactory.getInstance().getUtilDAO().findUserByLogin(login, connection);

        user.setLogin(newLogin);
        user.setPassword(newPassword);

        DAOFactory.getInstance().getCRUD(user).update(user, connection);
    }

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

    public void deleteWasEatenByFoodId(int idF, Connection connection) throws DBException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM " + new WasEatenCRUD().getTableName() + " WHERE " + new FoodCRUD().getTableIdRowName() + "=?";
        System.out.println(sql);

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (Integer)idF);
            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw new DBException("Not deleted");
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
