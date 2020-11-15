package com.geekbrains.spring.mvc.repositories.specifications;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> priceGEThan(int minPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLEThan(int maxPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> asc() {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaQuery.orderBy(criteriaBuilder.asc(root.get("price"))).getGroupRestriction();
    }

    public static Specification<Product> desc() {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaQuery.orderBy(criteriaBuilder.desc(root.get("price"))).getGroupRestriction();
    }
}
