package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class    Main {
    public static void main(String[] args)  {
        // реализуйте алгоритм здесь
        UserService userDao = new UserServiceImpl();

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
