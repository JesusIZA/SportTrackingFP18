package ua.jr.raichuk.DB.dao.impls.realdao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.jr.raichuk.DB.dao.CRUD;
import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.DB.utils.UtilSimpleConnection;
import ua.jr.raichuk.Exceptions.DBException;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class ProfileCRUDTest {
    Connection connection;
    CRUD<Profile> crud;
    @Before
    public void setUp() throws Exception {
        crud = DAOFactory.getInstance().getCRUD(new Profile());
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
        Profile entity = crud.findById(2, connection);
        Profile mustBe = new Profile(2, "Sarah", "Raichuk", "female", Date.valueOf("1997-06-1"), 174, 56, 35);

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
        Profile entity = new Profile("Test", "Test", "female", Date.valueOf("1997-06-2"), 174, 56, 35);
        crud.add(entity, connection);
        Profile inDB = crud.findById(entity.getIdP(), connection);

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
        Profile entity = crud.findById(2, connection);
        Profile newEntity = new Profile(2, "Test", "Test", "female", Date.valueOf("1997-06-2"), 164, 46, 25);

        entity.setName(newEntity.getName());
        entity.setSurname(newEntity.getSurname());
        entity.setSex(newEntity.getSex());
        entity.setBirthday(newEntity.getBirthday());
        entity.setHeight(newEntity.getHeight());
        entity.setWeight(newEntity.getWeight());
        entity.setActiveTime(newEntity.getActiveTime());
        crud.update(entity, connection);


        Profile inDB = crud.findById(2, connection);

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
        crud.delete(1, connection);
        Profile mustBeNull =  crud.findById(1, connection);

        assertTrue(Objects.isNull(mustBeNull));
    }

    @Test
    public void deleteBad() throws Exception {
        boolean flag = false;
        try {
            crud.delete(2, connection);
        } catch (DBException e) {
            flag = true;
        }
        assertTrue(flag);
    }

}