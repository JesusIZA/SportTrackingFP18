package ua.jr.raichuk.DB.dao.impls.realdao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.jr.raichuk.DB.dao.CRUD;
import ua.jr.raichuk.DB.entities.impls.WasEaten;
import ua.jr.raichuk.DB.utils.UtilSimpleConnection;
import ua.jr.raichuk.Exceptions.DBException;

import java.sql.Connection;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class WasEatenCRUDTest {
    Connection connection;
    CRUD<WasEaten> crud;
    @Before
    public void setUp() throws Exception {
        crud = DAOFactory.getInstance().getCRUD(new WasEaten());
        connection = UtilSimpleConnection.getConnection();
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws Exception {
        connection.rollback();
        connection.setAutoCommit(true);
        crud = null;
        connection = null;
    }

    @Test
    public void findAll() throws Exception {
        int quantity = crud.findAll(connection).size();
        int mustBe = 2;
        assertTrue(quantity == mustBe);
    }

    @Test
    public void getLastId() throws Exception {
        int id = crud.getLastId(connection);
        int mustBe = 2;
        assertTrue(id == mustBe);
    }

    @Test
    public void getAllIds() throws Exception {
        boolean flag = true;
        List<Integer> mustBe =Arrays.asList(1, 2);
        List<Integer> ids = crud.getAllIds(connection);

        if(mustBe.size() != ids.size()){
            flag = false;
        } else {
            for(int i = 0; i < ids.size(); i++){
                if(mustBe.get(i).intValue() != ids.get(i).intValue())
                    flag = false;
            }
        }

        assertTrue(flag);
    }

    @Test
    public void findById() throws Exception {
        boolean flag = true;
        WasEaten entity = crud.findById(2, connection);
        WasEaten mustBe = new WasEaten(2, 2, 1, Date.valueOf("2018-01-01"));

        if(!Objects.isNull(entity)) {
            if (!Objects.equals(entity, mustBe)) {
                flag = false;
            }
        } else {
            flag = false;
        }

        assertTrue(flag);
    }

    @Test
    public void add() throws Exception {
        boolean flag = true;
        WasEaten entity = new WasEaten(2, 1, Date.valueOf("2017-05-16"));
        crud.add(entity, connection);
        WasEaten inDB = crud.findById(entity.getIdWE(), connection);

        if(!Objects.isNull(inDB)) {
            if (!Objects.equals(entity, inDB)) {
                flag = false;
            }
        } else {
            flag = false;
        }
        assertTrue(flag);
    }

    @Test
    public void update() throws Exception {
        boolean flag = true;
        WasEaten entity = crud.findById(2, connection);
        WasEaten newEntity = new WasEaten(2,1, 2, Date.valueOf("2017-11-08"));

        entity.setIdP(newEntity.getIdP());
        entity.setIdF(newEntity.getIdF());
        entity.setDateWE(newEntity.getDateWE());
        crud.update(entity, connection);


        WasEaten inDB = crud.findById(2, connection);

        if(!Objects.isNull(inDB)) {
            if (!Objects.equals(newEntity, inDB)) {
                flag = false;
            }
        } else {
            flag = false;
        }
        assertTrue(flag);
    }

    @Test
    public void delete() throws Exception {
        crud.delete(2, connection);
        WasEaten mustBeNull =  crud.findById(2, connection);

        assertTrue(Objects.isNull(mustBeNull));
    }

}