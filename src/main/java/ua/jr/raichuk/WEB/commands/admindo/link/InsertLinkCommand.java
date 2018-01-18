package ua.jr.raichuk.WEB.commands.admindo.link;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminInsertCommand;
import ua.jr.raichuk.WEB.validators.NormValidator;
import ua.jr.raichuk.WEB.validators.ProfileValidator;
import ua.jr.raichuk.WEB.validators.UserValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jesus Raichuk
 */
public class InsertLinkCommand extends AdminInsertCommand<Link> {
    @Override
    protected Entity getEntity() {
        return new Link();
    }

    @Override
    protected String getRedirect() {
        return FactoryCommand.ADMIN_SHOW_LIST_LINK;
    }

    @Override
    protected Link getValidEntity(HttpServletRequest request) throws DataException {
        int idU = Integer.parseInt(request.getParameter("idUL"));
        int idP = Integer.parseInt(request.getParameter("idPL"));
        int idN = Integer.parseInt(request.getParameter("idNL"));

        if (ProfileValidator.isIdPExisting(idP) &&
                NormValidator.isIdNExisting(idN) &&
                UserValidator.isIdUExisting(idU)) {
            Link food = new Link(idU, idP, idN);
            return food;
        } else {
            throw new DataException("Data is incorrect");
        }
    }
}
