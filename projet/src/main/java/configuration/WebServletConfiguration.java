package configuration;

import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import servlets.ControllerServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebServletConfiguration implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webctx=new AnnotationConfigWebApplicationContext();
        webctx.register(ClientWebConfig.class);
        webctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet=servletContext.addServlet("controleur",new ControllerServlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(webctx);
        servletContext.addListener(contextLoaderListener);

        ServletRegistration.Dynamic h2servlet = servletContext
                .addServlet("h2-console", new WebServlet());
        h2servlet.setLoadOnStartup(2);
        h2servlet.addMapping("/console/*");
    }
}
