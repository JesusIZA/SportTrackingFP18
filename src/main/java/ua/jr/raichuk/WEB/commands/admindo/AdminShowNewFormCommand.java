package ua.jr.raichuk.WEB.commands.admindo;

import ua.jr.raichuk.Exceptions.TransactionException;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.services.admin.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Command show empty form for adding new cortege
 *
 * @author Jesus Raichuk
 */
public abstract class AdminShowNewFormCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(getRedirect()).forward(request, response);
    }
    protected abstract String getRedirect();
}
