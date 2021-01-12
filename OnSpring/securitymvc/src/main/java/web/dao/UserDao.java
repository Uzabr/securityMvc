package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);
    void updateUser(User user);
    User getUserById(Long id);
    void deleteUser(Long id);
    User getUserByName(String name);
    List<User> listUser();

}
