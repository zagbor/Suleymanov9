package ru.zagbor.practice.suleimanov.db;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import ru.zagbor.practice.suleimanov.model.Account;
import ru.zagbor.practice.suleimanov.model.AccountStatus;
import ru.zagbor.practice.suleimanov.model.Customer;
import ru.zagbor.practice.suleimanov.model.Specialty;

import java.util.Properties;


public class HibernateSessionFactory {

    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = HibernateSessionFactory.buildSessionFactory();
        }
        return sessionFactory;
    }


    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "org.postgresql.Driver");
        settings.put(Environment.URL, DatabaseConnectionsProperties.databaseConnectionsProperties.getUrl());
        settings.put(Environment.USER, DatabaseConnectionsProperties.databaseConnectionsProperties.getUsername());
        settings.put(Environment.PASS, DatabaseConnectionsProperties.databaseConnectionsProperties.getPassword());
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        configuration.setProperties(settings);
        configuration.addAnnotatedClass(Account.class);
        configuration.addAnnotatedClass(AccountStatus.class);
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Specialty.class);
        return configuration.buildSessionFactory();
    }
}
