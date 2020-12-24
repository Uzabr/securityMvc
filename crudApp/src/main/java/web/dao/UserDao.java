package web.dao;


import web.model.Users;

import java.util.List;

public interface UserDao {
    void addUsers(Users user);
    void updateUsers(Users users);
    void removeUsers(long id);
    Users getUsersByid(long id);
    List<Users> listUsers();

}
