package web.config.dbconfig;


import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource(value = {"classpath:db.properties"})
@EnableTransactionManagement
@EnableJpaRepositories("web")
public class DbConfig {


    @Autowired
    private  Environment environment;


    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("db.driver")));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));

        return dataSource;
    }

    @Bean(name = "entityManagerFactoryBean")
//    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entity = new LocalContainerEntityManagerFactoryBean();
        entity.setDataSource(getDataSource());

        Properties prop = new Properties();
        prop.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        prop.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        prop.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        prop.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        entity.setJpaProperties(prop);
        entity.setPackagesToScan("web");
//        entity.setPersistenceUnitName("JpaPersistenceUnit");
        entity.setJpaVendorAdapter(vendorAdapter());
        return entity;
    }
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactoryBean().getObject());
    }
    @Bean
    public JpaVendorAdapter vendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }
}
