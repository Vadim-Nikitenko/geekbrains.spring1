package com.geekbrains.spring.mvc.repositories.product;

import com.geekbrains.spring.mvc.model.Product;

import java.util.List;

public interface IProductDataProvider {
    List<Product> findAll();
    Product saveOrUpdateProduct(Product product);
    Product findById(Long id);
    void removeProduct(Long id);
}
