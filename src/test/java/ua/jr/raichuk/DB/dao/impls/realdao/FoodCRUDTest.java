package ua.jr.raichuk.DB.dao.impls.realdao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.jr.raichuk.DB.dao.CRUD;
import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.DB.utils.UtilSimpleConnection;
import ua.jr.raichuk.Exceptions.DBException;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class FoodCRUDTest {
    Connection connection;
    CRUD<Food> crud;
    @Before
    public void setUp() throws Exception {
        crud = DAOFactory.getInstance().getCRUD(new Food());
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
        Food entity = crud.findById(2, connection);
        Food mustBe = new Food(2, "Pasta", 113.4, 7.4, 5.3, 1.2);

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
    public void addGood() throws Exception {
        boolean flag = true;
        Food entity = new Food("Test",123.4, 23.4, 21.0, 19.8);
        crud.add(entity, connection);
        Food inDB = crud.findById(entity.getIdF(), connection);

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
    public void addBad() throws Exception {
        boolean flag = false;
        Food entity = new Food("Test",123.4, 23.4, 21.0, 19.8);
        crud.add(entity, connection);
        try {
            crud.add(entity, connection);
        } catch (DBException e) {
            flag = true;
        }

        assertTrue(flag);
    }

    @Test
    public void update() throws Exception {
        boolean flag = true;
        Food entity = crud.findById(2, connection);
        Food newEntity = new Food(2, "Test", 123.4, 8.4, 6.3, 2.2);

        entity.setName(newEntity.getName());
        entity.setCalories(newEntity.getCalories());
        entity.setProteins(newEntity.getProteins());
        entity.setFats(newEntity.getFats());
        entity.setCarbohydrates(newEntity.getCarbohydrates());
        crud.update(entity, connection);


        Food inDB = crud.findById(2, connection);

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
        Food mustBeNull =  crud.findById(2, connection);

        assertTrue(Objects.isNull(mustBeNull));
    }

    @Test
    public void deleteBad() throws Exception {
        boolean flag = false;
        try {
            crud.delete(3, connection);
        } catch (DBException e) {
            flag = true;
        }
        assertTrue(flag);
    }

}