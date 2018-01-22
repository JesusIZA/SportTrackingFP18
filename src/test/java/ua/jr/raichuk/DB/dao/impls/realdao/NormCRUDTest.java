package ua.jr.raichuk.DB.dao.impls.realdao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.jr.raichuk.DB.dao.CRUD;
import ua.jr.raichuk.DB.entities.impls.Norm;
import ua.jr.raichuk.DB.utils.UtilSimpleConnection;
import ua.jr.raichuk.Exceptions.DBException;

import java.sql.Connection;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class NormCRUDTest {
    Connection connection;
    CRUD<Norm> crud;
    @Before
    public void setUp() throws Exception {
        crud = DAOFactory.getInstance().getCRUD(new Norm());
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
        int mustBe = 3;
        assertTrue(quantity == mustBe);
    }

    @Test
    public void getLastId() throws Exception {
        int id = crud.getLastId(connection);
        int mustBe = 3;
        assertTrue(id == mustBe);
    }

    @Test
    public void getAllIds() throws Exception {
        boolean flag = true;
        List<Integer> mustBe =Arrays.asList(1, 2, 3);
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
        Norm entity = crud.findById(2, connection);
        Norm mustBe = new Norm(2, 2345, 256);

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
        Norm entity = new Norm(3232, 342);
        crud.add(entity, connection);
        Norm inDB = crud.findById(entity.getIdN(), connection);

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
        Norm entity = crud.findById(2, connection);
        Norm newEntity = new Norm(2,3232, 342);

        entity.setCalories(newEntity.getCalories());
        entity.setProteins(newEntity.getProteins());
        crud.update(entity, connection);


        Norm inDB = crud.findById(2, connection);

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
    public void deleteGood() throws Exception {
        crud.delete(2, connection);
        Norm mustBeNull =  crud.findById(2, connection);

        assertTrue(Objects.isNull(mustBeNull));
    }

    @Test
    public void deleteBad() throws Exception {
        boolean flag = false;
        try {
            crud.delete(1, connection);
        } catch (DBException e) {
            flag = true;
        }
        assertTrue(flag);
    }

}