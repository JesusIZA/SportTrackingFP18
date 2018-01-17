package ua.jr.raichuk.DB.dao.impls;


import ua.jr.raichuk.DB.dao.CRUD;
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
        System.out.println(sql);

        try{
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement = getFullSecondaryValuesPreparedStatement(preparedStatement, entity, 1);

            preparedStatement.executeUpdate();
            parseResultSetGeneratedKeys(preparedStatement.getGeneratedKeys(), entity);
        } catch (Exception e){
            e.printStackTrace();
            throw new DBException("Not added");
        }
    }


    @Override
    public List<T> findAll(Connection con) throws DBException {
        Connection connection = con;

        List<T> entities = new ArrayList<T>();

        String sql = "SELECT * FROM " + getTableName();
        System.out.println(sql);

        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                T entity = null;
                entity = fillEntity(resultSet);
                entities.add(entity);
            }
        } catch (Exception e){
            throw new DBException("Not found");
        } finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return entities;
    }

    @Override
    public int getLastId(Connection con) throws DBException {
        Connection connection = con;

        int lastId = 0;

        String sql = "SELECT " + getTableIdRowName() + " FROM " + getTableName();
        System.out.println(sql);

        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Integer> ids = new LinkedList<Integer>();
            while (resultSet.next()){
                T entity = null;
                ids.add(resultSet.getInt(getTableIdRowName()));
            }
            for (int i = 0; i<ids.size(); i++){
                if(lastId < ids.get(i)) {
                    lastId = ids.get(i);
                }
            }
        } catch (Exception e){
            throw new DBException("Not found");
        } finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return lastId;
    }

    @Override
    public T findById(int id, Connection con) throws DBException {
        Connection connection = con;

        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + getTableIdRowName() + "=?";
        System.out.println(sql);

        T entity = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (Integer)id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            resultSet.next();
            entity = fillEntity(resultSet);
            preparedStatement.execute();
        } catch (Exception e){
            new DBException("Not found");
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
        System.out.println(sql);

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement = getFullSecondaryValuesPreparedStatement(preparedStatement , entity, 1);
            preparedStatement = getFullIdValuePreparedStatement(preparedStatement, entity, getTableRowsNames().size());

            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw new DBException("Not updated");
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

    @Override
    public void delete(int id, Connection con) throws DBException {
        Connection connection = con;

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM " + getTableName() + " WHERE " + getTableIdRowName() + "=?";
        System.out.println(sql);

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (Integer)id);
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

    protected abstract String getTableName();
    protected abstract String getTableIdRowName();
    protected abstract List<String> getTableRowsNames();

    protected abstract void parseResultSetGeneratedKeys(ResultSet generatedKeys, T entity) throws DBException;

    protected abstract PreparedStatement getFullIdValuePreparedStatement(PreparedStatement preparedStatement, T t, int from) throws SQLException;
    protected abstract PreparedStatement getFullSecondaryValuesPreparedStatement(PreparedStatement preparedStatement, T t, int from) throws SQLException;
    protected abstract T fillEntity(ResultSet resultSet) throws SQLException;
}
