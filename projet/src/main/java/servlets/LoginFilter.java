package servlets;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * Ce filtre ne fait suivre au contrôleur que si l'utilisateur est loggué ou demande à se logguer.
 * (laisse aussi passer les appels à la console H2)
 */
@WebFilter(filterName = "LoginFilter",urlPatterns = "*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hr=(HttpServletRequest)req;
        String courant=(String)hr.getSession().getAttribute("courant");
        String todo=req.getParameter("TODO");
        System.out.println(hr.getRequestURI());
        if ( (hr.getRequestURI().contains("console"))
            || (courant!=null)||("login".equals(todo))
        ) {
            chain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

}
