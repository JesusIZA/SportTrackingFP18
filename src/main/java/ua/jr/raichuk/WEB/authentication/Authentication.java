package ua.jr.raichuk.WEB.authentication;

import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.transactions.Transaction;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.services.user.LoginService;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.Objects;

public abstract class Authentication {
    private Authentication() {
    }

    public static boolean isUserLogIn(HttpSession session){
        String login = (String)session.getAttribute("login");
        String password = "";
        boolean isIn = false;

        Connection connection = null;
        try {
            connection = Transaction.startTransaction();
            try {
                password = DAOFactory.getInstance().getUtilDAO().findUserByLogin(login, connection).getPassword();

                Transaction.commit(connection);
                isIn = (Objects.nonNull(login) || Objects.equals(login, "")) && LoginService.getService().verify(login, password);
            } catch (TransactionException transactionException) {
                isIn = false;
            } finally {
                Transaction.endTransaction(connection);
            }
        } catch (Exception e) {
            return false;
        }

        return isIn;
    }

    public static boolean isAdmin(HttpSession session) {
        String login = (String)session.getAttribute("login");
        if(Objects.equals(login, "Admin") || Objects.equals(login, "Admin2"))
            return true;
        return false;
    }
}
