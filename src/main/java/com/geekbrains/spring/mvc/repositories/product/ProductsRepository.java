package com.geekbrains.spring.mvc.repositories.product;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductsRepository implements IProductDataProvider {

    private List<Product> products;
    private Long maxId;

    @PostConstruct
    private void init() {
        this.products = new ArrayList<>();
        this.products.add(new Product(1L, "Honor 20", 20000));
        this.products.add(new Product(2L, "Samsung Galaxy J5", 15000));
        this.products.add(new Product(3L, "iPhone X", 50000));
        this.products.add(new Product(4L, "HTC One", 20000));
        this.maxId = 4L;
    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        if (product.getId() == null) {
            maxId++;
            product.setId(maxId);
            products.add(product);
            return product;
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())) {
                    products.set(i, product);
                    return product;
                }
            }
        }
        throw new RuntimeException("What???");
    }

    @Override
    public Product findById(Long id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new RuntimeException("Product not found");
    }

    @Override
    public void removeProduct(Long id) {
        try {
            products.removeIf(p -> p.getId().equals(id));
        } catch (Exception e) {
            throw new RuntimeException("Product not found");
        }
    }
}
