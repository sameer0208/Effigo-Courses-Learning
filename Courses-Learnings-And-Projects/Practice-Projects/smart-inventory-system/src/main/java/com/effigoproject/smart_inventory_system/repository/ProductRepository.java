package com.effigoproject.smart_inventory_system.repository;

import com.effigoproject.smart_inventory_system.entity.Product;
import com.effigoproject.smart_inventory_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByNameContaining(String name);
}
