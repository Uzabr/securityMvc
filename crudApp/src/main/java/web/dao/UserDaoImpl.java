package web.dao;

import com.sun.xml.internal.ws.developer.StreamingAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {



    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUsers (Users user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUsers (Users users) {
        entityManager.merge(users);
    }

    @Override
    public void removeUsers (long id) {
       Users users =  entityManager.find(Users.class, id);
       if (users != null) {
           entityManager.remove(users);
       }
    }

    @Override
    public Users getUsersByid (long id) {
        return entityManager.find(Users.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Users> listUsers () {
        return (List<Users>) entityManager.createQuery("from Users").getResultList();
    }
}
