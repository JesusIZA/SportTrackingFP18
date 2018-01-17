package ua.jr.raichuk.WEB.commands.admindo.food;

import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.commands.admindo.AdminShowNewFormCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jesus Raichuk
 */
public class ShowNewFoodFormCommand extends AdminShowNewFormCommand {
    @Override
    protected String getRedirect() {
        return "admin/foodForm.jsp";
    }
}
