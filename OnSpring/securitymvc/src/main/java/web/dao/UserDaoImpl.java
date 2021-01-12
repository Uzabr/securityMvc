package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository(value = "userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public void addUser (User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void updateUser (User user) {
        entityManager.merge(user);
    }


    @Transactional
    @Override
    public User getUserById (Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void deleteUser (Long id) {
        entityManager.remove(getUserById(id));
    }

    @Transactional
    @Override
    public User getUserByName (String name) {
        return (User) entityManager.createQuery("select user from User user where user.firstName=:name").setParameter("name", name).getSingleResult();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<User> listUser () {
        return (List<User>) entityManager.createQuery("from User").getResultList();
    }
}
