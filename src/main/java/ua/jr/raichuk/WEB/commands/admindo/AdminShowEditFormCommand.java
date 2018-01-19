package ua.jr.raichuk.WEB.commands.admindo;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.services.admin.AdminService;
import ua.jr.raichuk.WEB.services.admin.AdminServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jesus Raichuk
 */
public abstract class AdminShowEditFormCommand<T> implements Command {
    private static Logger LOGGER = Logger.getLogger(AdminShowEditFormCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            AdminService<T> adminService = AdminServiceFactory.getAdminService(getEntity());
            T t = adminService.getById(id);

            request.setAttribute("entity", t);

            request.getRequestDispatcher(getRedirect()).forward(request, response);
        } catch (TransactionException e) {
            LOGGER.error("DB.DAO(CRUD)->Command.Admin (AdminShowEditFormCommand.execute()) exception : read error!");
            request.setAttribute("error", "Food search error");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }

    protected abstract Entity getEntity();
    protected abstract String getRedirect();
}
