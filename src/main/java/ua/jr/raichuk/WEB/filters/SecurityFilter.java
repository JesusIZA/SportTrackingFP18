package ua.jr.raichuk.WEB.filters;


import org.apache.log4j.Logger;
import ua.jr.raichuk.WEB.authentication.Authentication;
import ua.jr.raichuk.WEB.configaration.SecurityConfiguration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class SecurityFilter implements Filter {
    private static Logger LOGGER = Logger.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        SecurityConfiguration configuration = SecurityConfiguration.getInstance();
        String command = request.getParameter("command");
        String role = configuration.security(command);

        if (Objects.equals(role, SecurityConfiguration.ALL)) {
            LOGGER.debug("Filter.Security (SecurityFilter.doFilter()) info : Successful request for ALL.");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (Objects.equals(role, SecurityConfiguration.AUTH)
                && Authentication.isUserLogIn(request.getSession())) {
            LOGGER.debug("Filter.Security (SecurityFilter.doFilter()) info : Successful request for AUTH.");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (Objects.equals(role, SecurityConfiguration.AUTH)
                && !Authentication.isUserLogIn(request.getSession())) {
            LOGGER.info("Filter.Security (SecurityFilter.doFilter()) info : Not authorised request for AUTH.");
            request.setAttribute("error", "Authentication failed");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        if (Objects.equals(role, SecurityConfiguration.ADMIN)
                && Authentication.isUserLogIn(request.getSession())
                && Authentication.isAdmin(request.getSession())) {
            LOGGER.debug("Filter.Security (SecurityFilter.doFilter()) info : Successful request for ADMIN.");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (Objects.equals(role, SecurityConfiguration.ADMIN)
                && !Authentication.isUserLogIn(request.getSession())) {
            LOGGER.warn("Filter.Security (SecurityFilter.doFilter()) info : Not authorised request for ADMIN.");
            request.setAttribute("error", "Authentication failed");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        if (Objects.equals(role, SecurityConfiguration.ADMIN)
                && !Authentication.isAdmin(request.getSession())) {
            LOGGER.warn("Filter.Security (SecurityFilter.doFilter()) info : Illegal request for ADMIN.");
            request.setAttribute("error", "Illegal request");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    public void destroy() {
    }
}
