package com.effigoproject.cacheredismanagement.repository;

import com.effigoproject.cacheredismanagement.entity.Product;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
