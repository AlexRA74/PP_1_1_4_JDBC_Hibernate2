package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;

public class    Main {
    public static void main(String[] args)  {
        // реализуйте алгоритм здесь
        UserDao userDao = (UserDao) new UserDaoJDBCImpl();

        userDao.createUsersTable();


        userDao.saveUser("Ivan","Phedorv",(byte) 32);
        userDao.saveUser("Viktoria","Philippova",(byte) 51);
        userDao.saveUser("Dmitriy", "Ivanov",(byte) 27);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
