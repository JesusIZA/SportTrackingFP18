package ua.jr.raichuk.WEB.services.user;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.entities.impls.*;
import ua.jr.raichuk.DB.transactions.Transaction;
import ua.jr.raichuk.Exceptions.DBException;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.Helpers.lists.PrintLists;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Jesus Raichuk
 */
public class TrackingService {
    private static Logger LOGGER = Logger.getLogger(TrackingService.class);

    private static TrackingService service = new TrackingService();

    public static TrackingService getService(){
        return service;
    }

    private TrackingService(){}

    public String getName(String login) throws TransactionException {
        String ret = "";
        Connection connection = Transaction.startTransaction();
        try{
            Profile profile = DAOFactory.getInstance().getUtilDAO().findProfileByLogin(login, connection);
            ret = profile.getName();

            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.DAO (TrackingService.getName()) exception : UtilDAO.findProfileByLogin error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        return ret;
    }

    public String getSurname(String login) throws TransactionException {
        String ret = "";
        Connection connection = Transaction.startTransaction();
        try{
            Profile profile = DAOFactory.getInstance().getUtilDAO().findProfileByLogin(login, connection);
            ret = profile.getSurname();

            Transaction.commit(connection);
        } catch (Exception e) {
            LOGGER.error("DB.DAO (TrackingService.getSurname()) exception : UtilDAO.findProfileByLogin error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        return ret;
    }

    public Norm getNorm(String login) throws TransactionException {
        Norm ret = null;
        Connection connection = Transaction.startTransaction();
        try{
            try {
                Link link = DAOFactory.getInstance().getUtilDAO().findLinkByLogin(login, connection);
                ret = (Norm) DAOFactory.getInstance().getCRUD(new Norm()).findById(link.getIdN(), connection);
            } catch (DBException e) {
                LOGGER.error("DB.DAO,CRUD (TrackingService.getNorm()) exception : UtilDAO,CRUD reading error!");
                User user = DAOFactory.getInstance().getUtilDAO().findUserByLogin(login, connection);
                Profile profile = DAOFactory.getInstance().getUtilDAO().findProfileByLogin(login, connection);
                ret = new Norm();
                ret.setCalories(UtilService.getNormCalories(profile));
                ret.setProteins(UtilService.getNormProteins(profile));

                DAOFactory.getInstance().getCRUD(ret).add(ret, connection);

                Link link = new Link(user.getIdU(), profile.getIdP(), ret.getIdN());
                DAOFactory.getInstance().getCRUD(link).add(link, connection);
            }

            Transaction.commit(connection);
        } catch (Exception e) {
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        return ret;
    }

    public Norm getForNow(String login) throws TransactionException {
        Norm forNow = new Norm();
        int calories = 0;
        int proteins = 0;

        List<Food> foods = getEatenToday(login, 0, 10000);
        for (int i = 0; i < foods.size(); i++) {
            calories += foods.get(i).getCalories();
            proteins += foods.get(i).getProteins();
        }
        forNow.setCalories(calories);
        forNow.setProteins(proteins);

        return forNow;
    }

    public List<Food> getEatenToday(String login, int start, int quantity) throws TransactionException {
        List<Food> eatenToday = new ArrayList<Food>();
        Connection connection = Transaction.startTransaction();
        try{
            Date today = new Date();

            Link link = DAOFactory.getInstance().getUtilDAO().findLinkByLogin(login, connection);

            List<WasEaten> wsList = DAOFactory.getInstance().getUtilDAO().findWasEatenByProfileId(link.getIdP(), connection);
            Collections.sort(wsList);
            Collections.reverse(wsList);

            if(start > wsList.size()) start = wsList.size() - (wsList.size()%quantity);
            if(start < 0) start = 0;
            for (int i = start; i < wsList.size() && i < start + quantity; i++) {
                if (wsList.get(i).getDateWE().getYear() == today.getYear() &&
                        wsList.get(i).getDateWE().getMonth() == today.getMonth() &&
                        wsList.get(i).getDateWE().getDay() == today.getDay()) {
                    Food food = (Food) DAOFactory.getInstance().getCRUD(new Food()).findById(wsList.get(i).getIdF(), connection);
                    System.out.println("l=" + food);
                    eatenToday.add(food);
                }
            }

            Transaction.commit(connection);
        } catch (DBException e) {
            LOGGER.error("DB.DAO,CRUD (TrackingService.getEatenToday()) exception : UtilDAO,CRUD reading error!");
            return eatenToday;
        } catch (Exception e) {
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        PrintLists.printListByRows(eatenToday);
        return eatenToday;
    }

    public String getMessage(Norm norm, Norm forNow) {
        String mass = "You can eat more! It is all right!";
        int status = getStatus(norm, forNow);
        switch (status){
            case -1: {
                mass = "Today enough. You've got too much!";
                break;
            }case 0: {
                mass = "This is your norm. Pay attention!";
                break;
            }
        }
        return mass;
    }
    public String getColor(Norm norm, Norm forNow) {
        String mass = "green";
        int status = getStatus(norm, forNow);
        switch (status){
            case -1: {
                mass = "red";
                break;
            }case 0: {
                mass = "orange";
                break;
            }
        }
        return mass;
    }

    public int getStatus(Norm norm, Norm forNow) {
        double deg = norm.getCalories() / 100;
        double status = (norm.getCalories() - forNow.getCalories()) / deg;
        if (status > 10)
            return 1;
        if (status < 10 && status > -5)
            return 0;
        else return -1;
    }

    public List<Food> getAllFoods() throws TransactionException {
        List<Food> foods = new ArrayList<Food>();
        Connection connection = Transaction.startTransaction();
        try{
            foods = DAOFactory.getInstance().getCRUD(new Food()).findAll(connection);

            Transaction.commit(connection);
        }catch (Exception e) {
            LOGGER.error("DB.CRUD (TrackingService.getAllFoods()) exception : CRUD findAll error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        return foods;
    }
}
