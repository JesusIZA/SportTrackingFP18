package ua.jr.raichuk.WEB.servlets;

import org.apache.log4j.Logger;
import ua.jr.raichuk.DB.utils.UtilConnectionPool;
import ua.jr.raichuk.WEB.commands.Command;
import ua.jr.raichuk.WEB.commands.FactoryCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDispatcher extends HttpServlet{
    private static Logger LOGGER = Logger.getLogger(ServletDispatcher.class);

    @Override
    public void init() throws ServletException {
        UtilConnectionPool.getDataSource();
        LOGGER.info("ConnectionPool (UtilConnectionPool) info : DataSource found.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);

    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        FactoryCommand factory = FactoryCommand.getInstance();
        Command command = factory.getCommand((String) req.getParameter("command"));
        LOGGER.debug(command);
        try {
            command.execute(req,resp);
        } catch (ServletException e) {
            LOGGER.debug("Servlet (ServletDispatcher.processRequest()) exception : " + e.getMessage());
        } catch (IOException e) {
            LOGGER.debug("Servlet.IO (ServletDispatcher.processRequest()) exception : " + e.getMessage());
        }
    }
}
