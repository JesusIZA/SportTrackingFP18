package ua.jr.raichuk.DB.dao;


import ua.jr.raichuk.Exceptions.DBException;

import java.sql.Connection;
import java.util.List;

public abstract class CRUD<T> {
    //create
    public abstract void add(T t, Connection con) throws DBException;

    //read
    public abstract List<T> findAll(Connection con) throws DBException;

    public abstract int getLastId(Connection con) throws DBException;

    public abstract List<Integer> getAllIds(Connection con) throws DBException;

    public abstract T findById(int id, Connection con) throws DBException;

    //update
    public abstract void update(T t, Connection con) throws DBException;

    //delete
    public abstract void delete(int id, Connection con) throws DBException;
}
