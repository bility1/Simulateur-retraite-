package servlets;


import entities.Groupe;
import entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.Guichet;
import entities.Membre;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ControllerServlet extends HttpServlet {
    @Autowired
    private Guichet guichet;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
        guichet.jeuDonne();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String todo=request.getParameter("TODO");
        if (todo==null) {todo="";}

        String courant=(String)request.getSession().getAttribute("courant");

        switch (todo) {
            case "login" :
                String login=request.getParameter("login");
                String password=request.getParameter("password");
                Membre m= guichet.getMembre(login);
                if ((m!=null)&& (m.getPasswd().equals(password))) {
                    request.getSession().setAttribute("courant",login);
                    mur(request,response);
                } else {
                    request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                }
                break;
            case "rediger" :
                    request.setAttribute("groupes",guichet.getGroupes());
                    request.getRequestDispatcher("WEB-INF/redaction.jsp").forward(request,response);
            break;
            case "publier":
                String message=request.getParameter("message");
                String groupe=request.getParameter("groupe");
                int grid=((groupe!=null)&&(groupe.length()>0))?Integer.parseInt(groupe):-1;
                guichet.publier(courant,message,grid);
                mur(request,response);
                break;
            case "logout" :
                request.getSession().removeAttribute("courant");
                request.getSession().invalidate();
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request,response);
                break;
            case "amis" :
                request.setAttribute("courant", guichet.getMembre((String)request.getSession().getAttribute("courant")));
                // TODO : travail accompli
                Membre me = guichet.getMembre(courant);
                request.setAttribute("suggestions",guichet.getMembres(me));
                request.getRequestDispatcher("WEB-INF/amis.jsp").forward(request,response);
                break;
            case "ami" :
                String membre=request.getParameter("membre");
                guichet.relier(courant,membre);
                mur(request,response);
                break;
            case "mur" :
            default:
                mur(request,response);
        }
    }


    private void mur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("courant", guichet.getMembre((String)request.getSession().getAttribute("courant")));
        // TODO : l'utilisateur ne doit voir que les messages de ses amis et de ses groupes
        String courant=(String)request.getSession().getAttribute("courant");

        Membre me = guichet.getMembre(courant);

        request.setAttribute("messages", guichet.getMessages(me));
        request.getRequestDispatcher("WEB-INF/mur.jsp").forward(request,response);
    }



}
