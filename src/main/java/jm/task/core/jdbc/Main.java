package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class    Main {
    public static void main(String[] args)  {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();


        userService.saveUser("Ivan","Phedorv",(byte) 32);
        userService.saveUser("Viktoria","Philippova",(byte) 51);
        userService.saveUser("Dmitriy", "Ivanov",(byte) 27);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
