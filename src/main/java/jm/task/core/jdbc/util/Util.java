package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import sun.awt.image.PixelConverter;

import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static sun.awt.image.PixelConverter.UshortGray.instance;

public class Util {
    private static String url = "jdbc:mysql://localhost:3306/MySql?useSSL=false&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "root";

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory;
        if (sessionFactory == null) {
            Properties properties = new Properties() {{
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                String hostName;
                hostName = null;
                setProperty("hibernate.connection.url", hostName);
                String userName = null;
                setProperty("hibernate.connection.username", userName);
                setProperty("hibernate.connection.password", password);
                String Driver = null;
                setProperty("hibernate.connection.driver_class", Driver);
                setProperty("show_sql", "true");
                setProperty("hibernate.hbm2ddl.auto", "create-drop");
                setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");
            }};

            Connection connection = null;
            {
            try {
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (SessionFactory) connection;
    }

    public static Util getInstance() {
        if (null == instance) instance = new PixelConverter();
        return (SessionFactory) instance;
    }
}