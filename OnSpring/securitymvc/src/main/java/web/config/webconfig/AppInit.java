package web.config.webconfig;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import web.config.dbconfig.DbConfig;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses () {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses () {
        return new Class[]{
                WebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings () {
        return new String[]{"/"};
    }
}
