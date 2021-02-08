package com.nisar.springReact.Config;

import com.nisar.springReact.JPA.EmployeeRepository;
import com.nisar.springReact.Models.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource(value = "persistence.properties")
public class DataSource {
    @Autowired
    Environment env;

    /*@Bean
    public javax.sql.DataSource sqliteSource(){
        final DriverManagerDataSource dtS = new DriverManagerDataSource();
        dtS.setDriverClassName(Objects.requireNonNull("org.sqlite.JDBC"));
        dtS.setUrl("jdbc:sqlite:C:/SqlLite/testDb.db");
        return dtS;
    }*/


    @Bean
    public javax.sql.DataSource sqliteDBSource(){
        final DriverManagerDataSource dtS = new DriverManagerDataSource();
        dtS.setDriverClassName(Objects.requireNonNull(env.getProperty("driverClassName")));
        dtS.setUrl(env.getProperty("url"));
        return dtS;
    }

    /*@Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory(javax.sql.DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(Employee.class.getPackage().getName());
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    public Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("ddl-auto"));
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.show_sql", env.getProperty("show-sql"));
                setProperty("hibernate.format_sql", env.getProperty("format-sql"));
            }
        };
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }*/

}
