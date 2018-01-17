package ua.jr.raichuk.WEB.commands.admindo;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.services.admin.AdminService;
import ua.jr.raichuk.WEB.services.admin.AdminServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Jesus Raichuk
 */
public abstract class AdminShowListCommand<T> implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AdminService<T> adminService = AdminServiceFactory.getAdminService(getEntity());
            List<T> ts = adminService.getAll();

            request.setAttribute("listEntity", ts);

            request.getRequestDispatcher(getRedirect()).forward(request, response);
        } catch (TransactionException e) {
            request.setAttribute("error", "Food search error");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }

    protected abstract Entity getEntity();
    protected abstract String getRedirect();
}
