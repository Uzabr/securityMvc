package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    User getUserByName(String name);
    User getUserById(Long id);
    void deleteUser(Long id);
    List<User> listUser();
    void addRole(Set<Role> role);
    Set<Role> getRole();
}
