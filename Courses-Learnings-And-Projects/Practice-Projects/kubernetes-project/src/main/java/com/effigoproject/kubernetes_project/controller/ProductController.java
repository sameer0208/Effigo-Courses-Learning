package com.effigoproject.kubernetes_project.controller;

import com.effigoproject.kubernetes_project.entity.Product;
import com.effigoproject.kubernetes_project.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAll();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.save(product);
    }
}