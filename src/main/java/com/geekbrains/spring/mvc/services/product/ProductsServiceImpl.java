package com.geekbrains.spring.mvc.services.product;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.repositories.product.IProductDataProvider;
import com.geekbrains.spring.mvc.repositories.product.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements IProductsService {
    private IProductDataProvider productsRepository;

    @Autowired
    public void setProductsRepository(@Qualifier(value = "productsRepository")IProductDataProvider productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        return productsRepository.saveOrUpdateProduct(product);
    }

    @Override
    public Product findById(Long id) {
        return productsRepository.findById(id);
    }

    @Override
    public void removeProduct(Long id) {
        productsRepository.removeProduct(id);
    }
}
