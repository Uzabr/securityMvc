package web.model;

import org.hibernate.annotations.GeneratorType;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(name = "name")
    private String nameProduct;

    @Column(name = "brand")
    private String brand;

    @Column(name = "country")
    private String country;

    @Column(name = "price")
    private double price;

    public Product () {
    }

    public Product (String nameProduct, String brand, String country, double price) {
        this.nameProduct = nameProduct;
        this.brand = brand;
        this.country = country;
        this.price = price;
    }

    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public String getNameProduct () {
        return nameProduct;
    }

    public void setNameProduct (String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getBrand () {
        return brand;
    }

    public void setBrand (String brand) {
        this.brand = brand;
    }

    public String getCountry () {
        return country;
    }

    public void setCountry (String country) {
        this.country = country;
    }

    public double getPrice () {
        return price;
    }

    public void setPrice (double price) {
        this.price = price;
    }

    @Override
    public String toString () {
        return "Product{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", brand='" + brand + '\'' +
                ", country='" + country + '\'' +
                ", price=" + price +
                '}';
    }
}
