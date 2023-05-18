package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import javax.transaction.SystemException;
import javax.transaction.Transaction;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private static final
    String CREATE_USERS_TABLE_SQL = "CREATE TABLE IF NOT EXISTS  users ( id BIGINT not NULL AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age TINYINT, PRIMARY KEY ( id ))";
    Transaction transaction = null;
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User" +
                    " (id BIGINT not null AUTO_INCREMENT,name VARCHAR(45)," +
                    "lastName VARCHAR(45)," +
                    " age INT, " +
                    "PRIMARY KEY(id))").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (SystemException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (SystemException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            User user =new User(name,lastName,age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (SystemException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (SystemException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            List<User> emps = session.createQuery("from User")
                    .getResultList();
            transaction.commit();
            return emps;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (SystemException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            final List<User> instance = session.createCriteria(User.class).list();
            for (User o: instance) {
                session.delete(o);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (SystemException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}