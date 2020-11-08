package com.geekbrains.spring.mvc.services.product;

import com.geekbrains.spring.mvc.model.Product;

import java.util.List;

public interface IProductsService {
    List<Product> findAll();
    Product saveOrUpdateProduct(Product product);
    Product findById(Long id);
    void removeProduct(Long id);
}
