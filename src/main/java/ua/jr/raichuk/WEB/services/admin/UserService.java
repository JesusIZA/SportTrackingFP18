package ua.jr.raichuk.WEB.services.admin;

import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.DB.transactions.Transaction;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.Connection;

/**
 * @author Jesus Raichuk
 */
public class UserService extends AdminService<User>{

    UserService(){}

    public void edit(String login, String newLogin, String newPassword) throws TransactionException {
        Connection connection = Transaction.startTransaction();
        try{
            DAOFactory.getInstance().getUtilDAO().editUser(login, newLogin, newPassword, connection);

            Transaction.commit(connection);
        } catch (Exception e) {
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
    }

    @Override
    public Entity getEmptyClass() {
        return new User();
    }
}
