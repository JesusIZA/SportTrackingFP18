package ua.jr.raichuk.WEB.commands.admindo.link;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Link;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminUpdateCommand;
import ua.jr.raichuk.WEB.validators.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jesus Raichuk
 */
public class UpdateLinkCommand extends AdminUpdateCommand<Link> {
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
        int id = Integer.parseInt(request.getParameter("id"));
        int idU = Integer.parseInt(request.getParameter("idUL"));
        int idP = Integer.parseInt(request.getParameter("idPL"));
        int idN = Integer.parseInt(request.getParameter("idNL"));

        if(LinkValidator.isIdLExisting(id) &&
                ProfileValidator.isIdPExisting(idP) &&
                NormValidator.isIdNExisting(idN) &&
                UserValidator.isIdUExisting(idU)){
            Link link = new Link(id, idU, idP, idN);
            return link;
        } else {
            throw new DataException("Data is incorrect");
        }
    }
}