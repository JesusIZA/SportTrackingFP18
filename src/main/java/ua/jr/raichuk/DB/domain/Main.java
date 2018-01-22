package ua.jr.raichuk.DB.domain;

import ua.jr.raichuk.DB.dao.CRUD;
import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.entities.impls.*;
import ua.jr.raichuk.DB.utils.UtilSimpleConnection;
import ua.jr.raichuk.Exceptions.DBException;
import ua.jr.raichuk.Helpers.lists.PrintLists;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * Class for testing DAO
 *
 * @author Jesus Raichuk
 */
public class Main{
/*
    //With public DAO Constructors!!!
    public static void main(String[] args) {
        //USERS
        UserDAO userDAO = new UserDAO();
        PrintLists.printListByRows(userDAO.findAll());
        System.out.println();

        System.out.println(userDAO.findById(1));
        System.out.println();

        User u = new User(4, "roma", "newroma");
        userDAO.add(u);
        PrintLists.printListByRows(userDAO.findAll());
        System.out.println();

        u.setLogin("rorik");
        userDAO.update(u);
        PrintLists.printListByRows(userDAO.findAll());
        System.out.println();

        userDAO.delete(u.getIdU());
        PrintLists.printListByRows(userDAO.findAll());
        System.out.println();

        userDAO.stopConnection();

        //FOODS
        FoodDAO foodDAO = new FoodDAO();
        PrintLists.printListByRows(foodDAO.findAll());
        System.out.println();

        System.out.println(foodDAO.findById(2));
        System.out.println();

        Food f = new Food(11, "test food", 34.4, 12.4, 0.3, 34.5);
        foodDAO.add(f);
        PrintLists.printListByRows(foodDAO.findAll());
        System.out.println();

        f.setName("neame ann");
        foodDAO.update(f);
        PrintLists.printListByRows(foodDAO.findAll());
        System.out.println();

        foodDAO.delete(f.getIdF());
        PrintLists.printListByRows(foodDAO.findAll());
        System.out.println();

        foodDAO.stopConnection();

        //PROFILES
        ProfileDAO profileDAO = new ProfileDAO();
        PrintLists.printListByRows(profileDAO.findAll());
        System.out.println();

        System.out.println(profileDAO.findById(2));
        System.out.println();

        Profile p = new Profile(4, "John", "Trok", 24, 178.9, 89.9, "middle", 35);
        profileDAO.add(p);
        PrintLists.printListByRows(profileDAO.findAll());
        System.out.println();

        p.setName("Goody");
        profileDAO.update(p);
        PrintLists.printListByRows(profileDAO.findAll());
        System.out.println();

        profileDAO.delete(p.getIdP());
        PrintLists.printListByRows(profileDAO.findAll());
        System.out.println();

        profileDAO.stopConnection();

        //WASEATEN
        WasEatenDAO wasEatenDAO = new WasEatenDAO();
        PrintLists.printListByRows(wasEatenDAO.findAll());
        System.out.println();

        System.out.println(wasEatenDAO.findById(2));
        System.out.println();

        WasEaten w = new WasEaten(19, 2, 4, new Date(118, 0, 3));
        wasEatenDAO.add(w);
        PrintLists.printListByRows(wasEatenDAO.findAll());
        System.out.println();

        w.setDateWE(new Date(118, 1, 4));
        wasEatenDAO.update(w);
        PrintLists.printListByRows(wasEatenDAO.findAll());
        System.out.println();

        wasEatenDAO.delete(w.getIdWE());
        PrintLists.printListByRows(wasEatenDAO.findAll());
        System.out.println();

        wasEatenDAO.stopConnection();
    }
*/

    static Connection connection = UtilSimpleConnection.getConnection();

    public static void main(String[] args) throws DBException {
        //User
/*        CRUD crud = DAOFactory.getInstance().getCRUD(new User());
        User u1 = new User("Saxh", "sapp");
        crud.add(u1, connection);
        PrintLists.printListByRows(crud.findAll(connection));

        u1.setPassword("fgfgfg");
        crud.update(u1, connection);
        PrintLists.printListByRows(crud.findAll(connection));
        System.out.println(crud.findById(u1.getIdU(), connection));

        crud.delete(u1.getIdU(), connection);
        PrintLists.printListByRows(crud.findAll(connection));*/
        //Profile
        /*
        crud = DAOFactory.getInstance().getCRUD(new Profile());
        Profile p1 = new Profile("Saxh", "sapp", "male", Date.valueOf("2008-04-05"), 122, 123, 20);
        crud.add(p1, connection);
        PrintLists.printListByRows(crud.findAll(connection));*/

        /*p1.setName("John");
        crud.update(p1, connection);
        PrintLists.printListByRows(crud.findAll(connection));
        System.out.println(crud.findById(p1.getIdP(), connection));

        crud.delete(p1.getIdP(), connection);
        PrintLists.printListByRows(crud.findAll(connection));*//*
        crud = DAOFactory.getInstance().getCRUD(new Norm());
        Norm norm = new Norm(1234, 12);
        crud.add(norm, connection);
        PrintLists.printListByRows(crud.findAll(connection));

        crud = DAOFactory.getInstance().getCRUD(new Link());
        Link link = new Link(u1.getIdU(),p1.getIdP(),norm.getIdN());
        crud.add(link, connection);
        PrintLists.printListByRows(crud.findAll(connection));*/

        //DAOFactory.getInstance().getUtilDAO().deleteProfile("Jesus2", connection);

        /*List<WasEaten> we = DAOFactory.getInstance().getUtilDAO().findWasEatenByProfileId(1, connection);
        PrintLists.printListByRows(we);

        UtilSimpleConnection.stopConnection(connection);*/

        Food f1 = new Food("Test food",123.4, 23.4, 21.0, 19.8);
        Food f2 = new Food("Test food",123.4, 23.4, 21.0, 19.8);

        System.out.println(Objects.equals(f1, f2));
    }

}
