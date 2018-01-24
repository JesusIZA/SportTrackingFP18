package ua.jr.raichuk.WEB.commands.admindo;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.Exceptions.DataException;
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
 * Command insert new cortege into DB
 *
 * @author Jesus Raichuk
 */
public abstract class AdminInsertCommand<T> implements Command {
    private static Logger LOGGER = Logger.getLogger(AdminInsertCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            T t = getValidEntity(request);
            AdminService adminService = AdminServiceFactory.getAdminService(getEntity());
            adminService.add(t);
            FactoryCommand.getInstance().getCommand(getRedirect()).execute(request, response);
        } catch (DataException e1) {
            LOGGER.debug("Command.Admin->Data (AdminInsertCommand.execute()) exception : Data is incorrect!");
            request.setAttribute("error", e1.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request,response);
        } catch (IllegalArgumentException e) {
            LOGGER.debug("Command.Admin->Data (AdminInsertCommand.execute()) exception : Data is illegal!");
            request.setAttribute("error", "Data is illegal");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        } catch (TransactionException e) {
            LOGGER.error("DB.DAO(CRUD)->Command.Admin (AdminInsertCommand.execute()) exception : insert error!");
            request.setAttribute("error", "Server error");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }

    protected abstract Entity getEntity();
    protected abstract String getRedirect();
    protected abstract T getValidEntity(HttpServletRequest request) throws DataException;
}
