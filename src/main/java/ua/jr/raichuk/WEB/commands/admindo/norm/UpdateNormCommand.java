package ua.jr.raichuk.WEB.commands.admindo.norm;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Norm;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminUpdateCommand;
import ua.jr.raichuk.WEB.validators.NormValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jesus Raichuk
 */
public class UpdateNormCommand extends AdminUpdateCommand<Norm> {
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
        int id = Integer.parseInt(request.getParameter("id"));
        Integer calories = Integer.valueOf(request.getParameter("caloriesN"));
        Integer proteins = Integer.valueOf(request.getParameter("proteinsN"));

        if(NormValidator.isValid(calories, proteins) &&
                NormValidator.isIdNExisting(id)){
            Norm entity = new Norm(id, calories, proteins);
            return entity;
        } else {
            throw new DataException("Data is incorrect");
        }
    }
}