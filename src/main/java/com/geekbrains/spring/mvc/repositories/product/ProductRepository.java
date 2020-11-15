package com.geekbrains.spring.mvc.repositories.product;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product findOneById(String name);
    List<Product> findAllByPriceGreaterThan(int price);

}

