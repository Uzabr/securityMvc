package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

@Service(value = "userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    private  UserDao userDao;


    private  RoleDao roleDao;

    @Autowired
    public void setUserDao (UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleDao (RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void addUser (User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser (User user) {
            userDao.updateUser(user);
    }


    @Override
    public User getUserByName (String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public User getUserById (Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void deleteUser (Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> listUser () {
        return userDao.listUser();
    }

    @Override
    public void addRole (Set<Role> role) {
            roleDao.addRole(role);
    }

    @Override
    public Set<Role> getRole () {
        return roleDao.getRole();
    }
}
