package com.geekbrains.spring.mvc.services.product;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private ProductRepository productRepository;

    @Autowired
    public void setStudentsRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAllByPriceGreaterThan(int price) {
        return productRepository.findAllByPriceGreaterThan(price);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void save(Product newProduct) {
        productRepository.save(newProduct);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findAll(Specification<Product> spec, Integer page) {
        if (page < 1) {
            page = 1;
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

}
