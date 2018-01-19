package ua.jr.raichuk.DB.dao.impls;


import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.dao.CRUD;
import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.Exceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class Generic for DAO
 *
 * @author Jesus Raichuk
 */
public abstract class CCRUD<T> extends CRUD<T> {
    private static Logger LOGGER = Logger.getLogger(CCRUD.class);

    @Override
    public void add(T entity, Connection con) throws DBException{
        Connection connection = con;

        PreparedStatement preparedStatement = null;

        List<String> ls = getTableRowsNames();
        StringBuilder rows = new StringBuilder(100);
        for (int i = 1; i < ls.size(); i++) {
            rows.append(ls.get(i));
            if(i < ls.size() - 1)
                rows.append(", ");
        }

        String sql = "INSERT INTO " + getTableName() + " (" + rows.toString() + ") VALUES (";
        for (int i = 1; i < getTableRowsNames().size(); i++) {
            if(i < getTableRowsNames().size() - 1)
                sql += "?, ";
            else
                sql += "?";
        }
        sql += ")";

        try{
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement = getFullSecondaryValuesPreparedStatement(preparedStatement, entity, 1);
            LOGGER.debug(preparedStatement);
            preparedStatement.executeUpdate();
            parseResultSetGeneratedKeys(preparedStatement.getGeneratedKeys(), entity);
        } catch (Exception e){
            LOGGER.error("Connection->SQL (CCRUD.add()) exception : " + ((Entity)entity).getClassName() + " not added!");
            throw new DBException("Not added");
        }
    }


    @Override
    public List<T> findAll(Connection con) throws DBException {
        Connection connection = con;

        List<T> entities = new ArrayList<T>();
        String sql = "SELECT * FROM " + getTableName();

        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            LOGGER.debug(sql);
            while (resultSet.next()){
                T entity = null;
                entity = fillEntity(resultSet);
                entities.add(entity);
            }
            if(statement != null)
                statement.close();
        } catch (Exception e){
            LOGGER.error("Connection->SQL (CCRUD.findAll()) exception : for Table=" + getTableName() + " findAll()!");
            throw new DBException("Not found");
        }
        return entities;
    }

    @Override
    public int getLastId(Connection con) throws DBException {
        Connection connection = con;

        int lastId = 0;

        String sql = "SELECT " + getTableIdRowName() + " FROM " + getTableName();

        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            LOGGER.debug(sql);
            List<Integer> ids = new LinkedList<Integer>();
            while (resultSet.next()){
                ids.add(resultSet.getInt(getTableIdRowName()));
            }
            for (int i = 0; i<ids.size(); i++){
                if(lastId < ids.get(i)) {
                    lastId = ids.get(i);
                }
            }
            if(statement != null)
                statement.close();
        } catch (Exception e){
            LOGGER.error("Connection->SQL (CCRUD.getLastId()) exception : for Table=" + getTableName() + " getLastId()");
            throw new DBException("Not found");
        }
        return lastId;
    }

    @Override
    public List<Integer> getAllIds(Connection con) throws DBException {
        Connection connection = con;

        String sql = "SELECT " + getTableIdRowName() + " FROM " + getTableName();
        List<Integer> ids = new ArrayList<Integer>();
        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            LOGGER.debug(sql);
            while (resultSet.next()){
                ids.add(resultSet.getInt(getTableIdRowName()));
            }
            if(statement != null)
                statement.close();
        } catch (Exception e){
            LOGGER.error("Connection->SQL (CCRUD.getLastId()) exception : for Table=" + getTableName() + " getAllIds()");
            throw new DBException("Not found");
        }
        return ids;
    }

    @Override
    public T findById(int id, Connection con) throws DBException {
        Connection connection = con;

        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + getTableIdRowName() + "=?";

        T entity = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (Integer)id);
            ResultSet resultSet = preparedStatement.executeQuery();
            LOGGER.debug(preparedStatement);
            resultSet.beforeFirst();
            resultSet.next();
            entity = fillEntity(resultSet);
            preparedStatement.execute();
            if(preparedStatement != null)
                preparedStatement.close();
        } catch (Exception e){
            LOGGER.error("Connection->SQL (CCRUD.findById()) exception : for Table=" + getTableName() + " findById() id=" + id);
            new DBException("Not found");
        }
        return entity;
    }

    @Override
    public void update(T entity, Connection con) throws DBException {
        Connection connection = con;

        PreparedStatement preparedStatement = null;

        List<String> ls = getTableRowsNames();
        String sql = "UPDATE " + getTableName() + " SET ";
        for (int i = 1; i < ls.size(); i++) {
            if(!ls.get(i).equals(getTableIdRowName())){
                if(i < ls.size() - 1)
                    sql += ls.get(i) + "=?, ";
                else
                    sql += ls.get(i) + "=? ";
            }
        }
        sql += "WHERE " + getTableIdRowName() + "=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement = getFullSecondaryValuesPreparedStatement(preparedStatement , entity, 1);
            preparedStatement = getFullIdValuePreparedStatement(preparedStatement, entity, getTableRowsNames().size());
            LOGGER.debug(preparedStatement);
            preparedStatement.executeUpdate();
            if(preparedStatement != null)
                preparedStatement.close();
        } catch (Exception e){
            LOGGER.error("Connection->SQL (CCRUD.update()) exception : " + ((Entity)entity).getClassName() + " not updated!");
            throw new DBException("Not updated");
        }
    }

    @Override
    public void delete(int id, Connection con) throws DBException {
        Connection connection = con;

        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM " + getTableName() + " WHERE " + getTableIdRowName() + "=?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (Integer)id);
            LOGGER.debug(preparedStatement);
            preparedStatement.executeUpdate();
            if(preparedStatement != null)
                preparedStatement.close();
        } catch (Exception e){
            LOGGER.error("Connection->SQL (CCRUD.delete()) exception : for Table=" + getTableName() + " by id=" + id + "!");
            throw new DBException("Not deleted");
        }
    }

    protected abstract String getTableName();
    protected abstract String getTableIdRowName();
    protected abstract List<String> getTableRowsNames();

    protected abstract void parseResultSetGeneratedKeys(ResultSet generatedKeys, T entity) throws DBException;

    protected abstract PreparedStatement getFullIdValuePreparedStatement(PreparedStatement preparedStatement, T t, int from) throws SQLException;
    protected abstract PreparedStatement getFullSecondaryValuesPreparedStatement(PreparedStatement preparedStatement, T t, int from) throws SQLException;
    protected abstract T fillEntity(ResultSet resultSet) throws SQLException;
}
