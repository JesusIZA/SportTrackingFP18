package ua.jr.raichuk.WEB.services.user;


import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.DB.transactions.Transaction;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.Connection;
import java.util.Objects;

public class LoginService {
    private static Logger LOGGER = Logger.getLogger(LoginService.class);

    private static LoginService service = new LoginService();

    public static LoginService getService(){
        return service;
    }

    private LoginService(){}

    /**
     * Verify is user login and password right
     * @param login - login user
     * @param password - password useer
     * @return boolean value - is verify or not
     * @throws TransactionException - if has some problem with DB
     */
    public boolean verify(String login, String password) throws TransactionException {
        Connection connection = Transaction.startTransaction();
        boolean ret = false;
        try {
            User user = DAOFactory.getInstance().getUtilDAO().findUserByLogin(login, connection);
            Transaction.commit(connection);

            ret = (Objects.nonNull(user) && user.getPassword().equals(password));
        } catch (Exception e) {
            LOGGER.error("DB.DAO (LoginService.verify()) exception : UtilDAO.findUserByLogin get User error!");
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
        return  ret;
    }
}
