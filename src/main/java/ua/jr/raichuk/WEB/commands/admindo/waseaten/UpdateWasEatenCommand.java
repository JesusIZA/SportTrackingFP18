package ua.jr.raichuk.WEB.commands.admindo.waseaten;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.WasEaten;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminUpdateCommand;
import ua.jr.raichuk.WEB.validators.FoodValidator;
import ua.jr.raichuk.WEB.validators.ProfileValidator;
import ua.jr.raichuk.WEB.validators.WasEatenValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * @author Jesus Raichuk
 */
public class UpdateWasEatenCommand extends AdminUpdateCommand<WasEaten> {
    @Override
    protected Entity getEntity() {
        return new WasEaten();
    }

    @Override
    protected String getRedirect() {
        return FactoryCommand.ADMIN_SHOW_LIST_WASEATEN;
    }

    @Override
    protected WasEaten getValidEntity(HttpServletRequest request) throws DataException {
        int id = Integer.parseInt(request.getParameter("id"));
        Integer idP = Integer.valueOf(request.getParameter("idPWE"));
        Integer idF = Integer.valueOf(request.getParameter("idFWE"));
        Date date = Date.valueOf(request.getParameter("dateWE"));

        if(WasEatenValidator.isValid(date) &&
                WasEatenValidator.isIdWEExisting(id)&&
                FoodValidator.isIdFExisting(idF) &&
                ProfileValidator.isIdPExisting(idP)){
            WasEaten entity = new WasEaten(id, idP, idF, date);
            return entity;
        } else {
            throw new DataException("Data is incorrect");
        }
    }
}