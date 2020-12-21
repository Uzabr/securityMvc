package web.dao;

import web.model.Product;

import java.util.List;

public interface ProductDao {
    void addProduct(Product product);

    void updateProduct(Product product);

    void removeProduct(long id);

    Product getProductById(long id);

    List<Product> listProducts();
}
