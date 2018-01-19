package ua.jr.raichuk.WEB.commands.admindo;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.services.admin.AdminService;
import ua.jr.raichuk.WEB.services.admin.AdminServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jesus Raichuk
 */
public abstract class AdminDeleteCommand<T> implements Command {
    private static Logger LOGGER = Logger.getLogger(AdminDeleteCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            AdminService<T> adminService = AdminServiceFactory.getAdminService(getEntity());
            adminService.delete(id);
            FactoryCommand.getInstance().getCommand(getRedirect()).execute(request, response);

        } catch (TransactionException e) {
            LOGGER.error("DB.DAO(CRUD)->Command.Admin (AdminDeleteCommand.execute()) exception : delete error!");
            request.setAttribute("error", "You must before delete all refers on this");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        } catch (Exception e) {
            LOGGER.debug("Command.Admin->Data (AdminDeleteCommand.execute()) exception : Data is incorrect!");
            request.setAttribute("error", "Data is illegal");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }

    protected abstract Entity getEntity();
    protected abstract String getRedirect();
}
