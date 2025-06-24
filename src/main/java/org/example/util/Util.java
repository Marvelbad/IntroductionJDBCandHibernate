package org.example.util;

import lombok.experimental.UtilityClass;
import org.example.bean.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

@UtilityClass
public class Util {
    private String url = "jdbc:mysql://localhost:3306/test";
    private String userName = "root";
    private String password = "root";
    private SessionFactory sessionFactory;

    public Connection getJDBCConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Соединение не установлено");
            throw new RuntimeException(e);
        }
        return connection;
    }

    public SessionFactory getHibernateConnection() {
        if (Objects.isNull(sessionFactory)) {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.put(Environment.URL, url);
            properties.put(Environment.USER, userName);
            properties.put(Environment.PASS, password);
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "create");

            configuration.setProperties(properties);

            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public SessionFactory getSessionFactoryProperties() {
        if (Objects.isNull(sessionFactory)) {
            Configuration configuration = new Configuration();
            configuration.addPackage("org\\example\\bean\\");
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        if (Objects.isNull(sessionFactory)) {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
