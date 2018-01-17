package ua.jr.raichuk.WEB.services.user;


import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.DB.transactions.Transaction;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.Connection;
import java.util.Objects;

public class LoginService {
    private static LoginService service = new LoginService();

    public static LoginService getService(){
        return service;
    }

    private LoginService(){}

    public boolean verify(String login, String password) throws TransactionException {
        Connection connection = Transaction.startTransaction();
        boolean ret = false;
        try {
            User user = DAOFactory.getInstance().getUtilDAO().findUserByLogin(login, connection);
            Transaction.commit(connection);

            ret = (Objects.nonNull(user) && user.getPassword().equals(password));
        } catch (Exception e) {
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        return  ret;
    }
}
