package com.effigoproject.cacheredismanagement.service;

import com.effigoproject.cacheredismanagement.entity.Product;
import com.effigoproject.cacheredismanagement.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    // Save a new product and clear cache
    @CacheEvict(value = "products", allEntries = true)
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Get product by ID (caches result)
    @Cacheable(value = "products", key = "#id")
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Get all products (caches result)
    @Cacheable(value = "products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Delete product and evict cache
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}