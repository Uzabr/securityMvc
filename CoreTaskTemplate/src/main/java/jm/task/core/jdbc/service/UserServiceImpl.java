package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl dap = new UserDaoJDBCImpl();
    public void createUsersTable() {
        dap.createUsersTable();
    }

    public void dropUsersTable() {
        dap.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        dap.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        dap.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return dap.getAllUsers();
    }

    public void cleanUsersTable() {
        dap.cleanUsersTable();
    }
}
