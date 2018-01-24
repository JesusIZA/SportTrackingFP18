package ua.jr.raichuk.DB.dao.impls.realdao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.jr.raichuk.DB.dao.CRUD;
import ua.jr.raichuk.DB.entities.impls.Food;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.DB.entities.impls.WasEaten;
import ua.jr.raichuk.DB.utils.UtilSimpleConnection;

import java.sql.Connection;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class UtilDAOTest {
    UtilDAO dao;
    Connection connection;
    @Before
    public void setUp() throws Exception {
        dao = DAOFactory.getInstance().getUtilDAO();
        connection = UtilSimpleConnection.getConnection();
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws Exception {
        connection.rollback();
        connection.setAutoCommit(true);
        connection = null;
        dao = null;
    }
    @Test
    public void findUserByLogin() throws Exception {
        boolean flag = true;
        User entity = dao.findUserByLogin("UserVasya", connection);
        User mustBe = new User(2, "UserVasya", "12");

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
    public void findFoodByName() throws Exception {
        boolean flag = true;
        Food entity = dao.findFoodByName("Borsch", connection);
        Food mustBe = new Food(3, "Borsch", 89.4, 5.4, 3.3, 1.4);

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
    public void findLinkByLogin() throws Exception {
        boolean flag = true;
        Link entity = dao.findLinkByLogin("UserVasya", connection);
        Link mustBe = new Link(1 ,2, 2, 1);

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
    public void findWasEatenByLogin() throws Exception {
        boolean flag = true;
        List<WasEaten> entities = dao.findWasEatenByLogin("UserVasya", connection);
        List<WasEaten> mustBe = Arrays.asList(new WasEaten(1 ,2, 3, Date.valueOf("2018-01-01")),
                new WasEaten(2, 2, 1, Date.valueOf("2018-01-01")));


        if(mustBe.size() != entities.size()){
            flag = false;
        } else {
            for(int i = 0; i < entities.size(); i++){
                if(!Objects.isNull(entities.get(i))) {
                    if (!Objects.equals(entities.get(i), mustBe.get(i))) {
                        flag = false;
                    }
                } else {
                    flag = false;
                }
            }
        }

        assertTrue(flag);
    }

    @Test
    public void findWasEatenByProfileId() throws Exception {
        boolean flag = true;
        List<WasEaten> entities = dao.findWasEatenByProfileId(2, connection);
        List<WasEaten> mustBe = Arrays.asList(new WasEaten(1 ,2, 3, Date.valueOf("2018-01-01")),
                new WasEaten(2, 2, 1, Date.valueOf("2018-01-01")));


        if(mustBe.size() != entities.size()){
            flag = false;
        } else {
            for(int i = 0; i < entities.size(); i++){
                if(!Objects.isNull(entities.get(i))) {
                    if (!Objects.equals(entities.get(i), mustBe.get(i))) {
                        flag = false;
                    }
                } else {
                    flag = false;
                }
            }
        }

        assertTrue(flag);
    }

    @Test
    public void deleteWasEatenByFoodId() throws Exception {
        dao.deleteWasEatenByFoodId(1, connection);
        CRUD<WasEaten> crud = DAOFactory.getInstance().getCRUD(new WasEaten());
        WasEaten mustBeNull =  crud.findById(2, connection);

        assertTrue(Objects.isNull(mustBeNull));
    }

}