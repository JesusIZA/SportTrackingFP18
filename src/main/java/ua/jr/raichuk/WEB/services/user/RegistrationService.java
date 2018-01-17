package ua.jr.raichuk.WEB.services.user;

import ua.jr.raichuk.DB.dao.impls.realdao.DAOFactory;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.DB.entities.impls.Norm;
import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.DB.entities.impls.User;
import ua.jr.raichuk.DB.transactions.Transaction;
import ua.jr.raichuk.Exceptions.TransactionException;

import java.sql.Connection;

public class RegistrationService {
    private static RegistrationService service = new RegistrationService();

    public static RegistrationService getService(){
        return service;
    }

    private RegistrationService(){}

    public void register(User user, Profile profile) throws TransactionException {
        Connection connection = Transaction.startTransaction();
        try {
            int normCalories = UtilService.getNormCalories(profile);
            int normProteins = UtilService.getNormProteins(profile);
            Norm norm = new Norm(normCalories, normProteins);

            DAOFactory.getInstance().getUtilDAO().addProfile(user, profile, norm, connection);


            Transaction.commit(connection);

            Link link = new Link(user.getIdU(), profile.getIdP(), norm.getIdN());
            DAOFactory.getInstance().getCRUD(link).add(link, connection);

            Transaction.commit(connection);
        } catch (Exception e) {
            e.printStackTrace();
            Transaction.rollback(e, connection);
        } finally {
            Transaction.endTransaction(connection);
        }
    }


}
