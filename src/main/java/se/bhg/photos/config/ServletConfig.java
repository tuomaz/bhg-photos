package se.bhg.photos.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ServletConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("se.bhg.photos.config");
        servletContext.addListener(new ContextLoaderListener(context));
        
        ServletRegistration.Dynamic applicationServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        applicationServlet.setLoadOnStartup(1);
        applicationServlet.setAsyncSupported(true);
        applicationServlet.addMapping("/"); 
    }
}
