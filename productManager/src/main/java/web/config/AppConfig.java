package web.config;


import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.model.Product;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName(environment.getProperty("db.driver"));
        managerDataSource.setUrl(environment.getProperty("db.url"));
        managerDataSource.setUsername(environment.getProperty("db.username"));
        managerDataSource.setPassword(environment.getProperty("db.password"));
        return managerDataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        Properties prop = new Properties();
        prop.put("org.hibernate.dialect.MySQL8Dialect", environment.getProperty("hibernate.dialect"));
        prop.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        prop.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        prop.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        factoryBean.setHibernateProperties(prop);
        factoryBean.setAnnotatedClasses(Product.class);

        return factoryBean;
    }


    @Bean
    public HibernateTransactionManager getTransactionManager(){
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(getSessionFactory().getObject());

        return manager;
    }
}
