package web.service;

import web.model.Users;

import java.util.List;

public interface UserService {
    void addUsers(Users users);
    void updateUsers(Users users);
    void removeUsers(long id);
    Users getUsersById(long id);
    List<Users> listUsers();
}
