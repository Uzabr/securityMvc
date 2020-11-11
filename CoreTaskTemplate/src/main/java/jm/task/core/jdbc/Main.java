package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao dao = new UserDaoJDBCImpl();
                dao.dropUsersTable();
        System.out.println("Таблица USER  удалена ");
        dao.createUsersTable();
        System.out.println("Таблица USER  создана ");

        User user1 = new User("Oleg", "Semyonov", (byte)25);
        dao.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User с именем - " + user1.getName() + " добавлен в базу данных");

        User user2 = new User("Maria", "Sharapova", (byte)35);
        dao.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User с именем - " + user2.getName() + " добавлен в базу данных");

        User user3 = new User("Ivan", "Olegov", (byte)21);
        dao.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User с именем - " + user3.getName() + " добавлен в базу данных");

        User user4 = new User("Jamal", "Malik", (byte)15);
        dao.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User с именем - " + user4.getName() + " добавлен в базу данных");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        System.out.println(dao.getAllUsers().toString());

    }
}
