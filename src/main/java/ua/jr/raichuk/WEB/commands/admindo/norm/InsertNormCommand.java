package ua.jr.raichuk.WEB.commands.admindo.norm;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Norm;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminInsertCommand;
import ua.jr.raichuk.WEB.validators.NormValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jesus Raichuk
 */
public class InsertNormCommand extends AdminInsertCommand<Norm> {
    @Override
    protected Entity getEntity() {
        return new Norm();
    }

    @Override
    protected String getRedirect() {
        return FactoryCommand.ADMIN_SHOW_LIST_NORM;
    }

    @Override
    protected Norm getValidEntity(HttpServletRequest request) throws DataException {
        Integer calories = Integer.valueOf(request.getParameter("caloriesN"));
        Integer proteins = Integer.valueOf(request.getParameter("proteinsN"));

        if (NormValidator.isValid(calories, proteins)) {
            Norm entity = new Norm(calories, proteins);
            return entity;
        } else {
            throw new DataException("Data is incorrect");
        }
    }
}
