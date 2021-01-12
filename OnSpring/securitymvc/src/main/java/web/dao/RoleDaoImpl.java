package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository(value = "roleDao")
public class RoleDaoImpl implements RoleDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void addRole (Set<Role> role) {
        role.forEach(roles -> entityManager.persist(roles));
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public Set<Role> getRole () {
        List<Role> list =  entityManager.createQuery("from Role").getResultList();
        return new HashSet<>(list);

    }
}
