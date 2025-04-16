package com.effigoproject.kubernetes_project.repository;

import com.effigoproject.kubernetes_project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}