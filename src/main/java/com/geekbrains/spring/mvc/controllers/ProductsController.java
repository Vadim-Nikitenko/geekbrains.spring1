package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.repositories.specifications.ProductSpecifications;
import com.geekbrains.spring.mvc.services.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showAllProducts(Model model,
                                  @RequestParam(name = "p", defaultValue = "1") Integer pageNumber,
                                  @RequestParam(name = "min_price", required = false) Integer minPrice,
                                  @RequestParam(name = "max_price", required = false) Integer maxPrice,
                                  @RequestParam(name = "sort", required = false) Integer sort) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGEThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLEThan(maxPrice));
        }
        if (sort != null) {
            if (sort == 1) {
                spec = spec.and(ProductSpecifications.asc());
            } else if (sort == 2) {
                spec = spec.and(ProductSpecifications.desc());
            }
        }

        List<Product> products = productsService.findAll(spec, pageNumber).getContent();
        model.addAttribute("products", products);
        return "all_products";
    }

//    @GetMapping
//    public String showAllProducts(Model model,
//                          @RequestParam(name = "min_price", required = false) Integer minPrice,
//                          @RequestParam(name = "max_price", required = false) Integer maxPrice) {
//        List<Product> products;
//        if (minPrice != null && maxPrice != null) {
//            products = productsService.findAllByPriceGreaterThanEqualAndPriceIsLessThanEqual(minPrice, maxPrice);
//        } else if (minPrice != null) {
//            products = productsService.findAllByPriceIsGreaterThanEqual(minPrice);
//        } else  if (maxPrice != null) {
//            products = productsService.findAllByPriceIsLessThanEqual(maxPrice);
//        } else {
//            products = productsService.findAll();
//        }
//        model.addAttribute("products", products);
//        return "all_products";
//    }


    @GetMapping("/add")
    public String showAddForm() {
        return "add_product_form";
    }

    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute Product newProduct) {
        productsService.save(newProduct);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productsService.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product modifiedProduct) {
        productsService.save(modifiedProduct);
        return "redirect:/products/";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable Long id) {
        productsService.deleteById(id);
        return "redirect:/products/";
    }


}
