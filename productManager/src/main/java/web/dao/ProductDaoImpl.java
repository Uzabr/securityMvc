package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Product;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProduct (Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);

    }

    @Override
    public void updateProduct (Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(product);
    }

    @Override
    public void removeProduct (long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product =  session.load(Product.class, id);
        if (product != null) {
            session.delete(product);
        }
    }

    @Override
    public Product getProductById (long id) {
         Session session =sessionFactory.getCurrentSession();
         return session.get(Product.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> listProducts () {
        Session session = sessionFactory.getCurrentSession();
        return (List<Product>) session.createQuery("from Product").getResultList();
    }
}
