package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDaoImpl;
import web.model.Users;

import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoImpl userDao;


    @Override
    @Transactional
    public void addUsers (Users users) {
        userDao.addUsers(users);
    }

    @Override
    @Transactional
    public void updateUsers (Users users) {
        userDao.updateUsers(users);
    }

    @Override
    @Transactional
    public void removeUsers (long id) {
        userDao.removeUsers(id);
    }

    @Override
    public Users getUsersById (long id) {
        return userDao.getUsersByid(id);
    }

    @Override
    @Transactional
    public List<Users> listUsers () {
        return userDao.listUsers();
    }
}
