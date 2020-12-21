package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.ProductDao;
import web.model.Product;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional
    public void addProduct (Product product) {
        productDao.addProduct(product);
    }

    @Override
    @Transactional
    public void updateProduct (Product product) {
        productDao.updateProduct(product);
    }

    @Override
    @Transactional
    public void removeProduct (long id) {
        productDao.removeProduct(id);
    }

    @Override
    @Transactional
    public Product getProductById (long id) {
        return productDao.getProductById(id);
    }

    @Override
    @Transactional
    public List<Product> listProducts () {
        return productDao.listProducts();
    }
}
