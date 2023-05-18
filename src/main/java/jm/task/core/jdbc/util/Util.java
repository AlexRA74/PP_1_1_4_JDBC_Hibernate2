package jm.task.core.jdbc.util;

import com.fasterxml.classmate.AnnotationConfiguration;
import jm.task.core.jdbc.model.User;
import org.hibernate.AnnotationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;


public class Util {
    public static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/MySql?useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root";
    private static SessionFactory sessionFactory;
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(Driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//                properties.put("hibernate.connection.url", hostName);
                properties.put("hibernate.connection.username", user);
                properties.put("hibernate.connection.password", password);
                properties.put("hibernate.connection.driver_class", Driver);
                properties.put("show_sql", "true");
                properties.put("hibernate.hbm2ddl.auto", "create-drop");
                properties.put("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return sessionFactory;
        }
    }
