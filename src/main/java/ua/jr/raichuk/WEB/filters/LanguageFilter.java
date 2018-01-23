package ua.jr.raichuk.WEB.filters;

import com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;
import ua.jr.raichuk.WEB.validators.EnterDataValidator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Filter set encoding is needed
 *
 * @author Jesus Raichuk
 */
public class LanguageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String lang = request.getParameter("language");
        if(!Objects.isNull(lang) && !Objects.equals(lang, "") && EnterDataValidator.isValidLanguage(lang)){
            HttpSession session = request.getSession();
            session.setAttribute("language", lang);
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
