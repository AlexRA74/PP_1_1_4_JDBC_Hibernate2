package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import jm.hibernate.SessionFactory;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory();
        try {
            session.beginTransaction();
            session.createSQLQuery(
                    "CREATE TABLE IF NOT EXISTS users(" +
                            "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                            "name VARCHAR(64), " +
                            "last_name VARCHAR(64), " +
                            "PRIMARY KEY (id)" +
                            "age INT null," + ")").addEntity(User.class).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Такая таблица уже существует");
        }

    }

    @Override
    public void dropUsersTable() {
        Session session =  Util.getSessionFactory();
        try {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE user").addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Такой таблицы не существует");
        }


    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session =  Util.getSessionFactory();
        User user = new User(name, lastName, age);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }


    @Override
    public void removeUserById(long id) {
        Session session =  Util.getSessionFactory();
        session.beginTransaction();
        session.delete(session.get(User.class, id));
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session =  Util.getSessionFactory();
        session.beginTransaction();
        List<User> list = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session =  Util.getSessionFactory();
        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();


    }
}
