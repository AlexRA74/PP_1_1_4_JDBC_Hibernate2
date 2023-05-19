package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import jm.task.core.jdbc.model.User;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соединения с БД



    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test?useSSL=false";
    private static final String NAME = "root";
    private static final String PASS = "270798Ra/";
    private static final String DIALECT = "org.hibernate.dialect.MySQL5Dialect";


    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            Configuration configuration = new Configuration();
            configuration.setProperty(Environment.DRIVER,DRIVER);
            configuration.setProperty(Environment.URL, URL);
            configuration.setProperty(Environment.USER, NAME);
            configuration.setProperty(Environment.PASS, PASS);
            configuration.setProperty(Environment.DIALECT, DIALECT);
            configuration.setProperty(Environment.SHOW_SQL, "true");
            configuration.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            configuration.addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }






    public static Connection getConnect(){
        try {
            return DriverManager.getConnection(URL,
                    NAME,
                    PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}