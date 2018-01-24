package ua.jr.raichuk.DB.dao.impls.realdao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.jr.raichuk.DB.dao.CRUD;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.DB.utils.UtilSimpleConnection;

import java.sql.Connection;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class LinkCRUDTest {
    Connection connection;
    CRUD<Link> crud;
    @Before
    public void setUp() throws Exception {
        crud = DAOFactory.getInstance().getCRUD(new Link());
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
        int mustBe = 1;
        assertTrue(quantity == mustBe);
    }

    @Test
    public void getLastId() throws Exception {
        int id = crud.getLastId(connection);
        int mustBe = 1;
        assertTrue(id == mustBe);
    }

    @Test
    public void getAllIds() throws Exception {
        boolean flag = true;
        List<Integer> mustBe =Arrays.asList(1);
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
        Link entity = crud.findById(1, connection);
        Link mustBe = new Link(1, 2, 2, 1);

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
        Link entity = new Link(3, 1, 3);
        crud.add(entity, connection);
        Link inDB = crud.findById(entity.getIdL(), connection);

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
        Link entity = crud.findById(1, connection);
        Link newEntity = new Link(1, 3,1,2);

        entity.setIdU(newEntity.getIdU());
        entity.setIdP(newEntity.getIdP());
        entity.setIdN(newEntity.getIdN());
        crud.update(entity, connection);


        Link inDB = crud.findById(1, connection);

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
        crud.delete(1, connection);
        Link mustBeNull =  crud.findById(1, connection);

        assertTrue(Objects.isNull(mustBeNull));
    }

}