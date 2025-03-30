package com.effigoproject.smart_inventory_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String supplier;

    private String description;

    @Column(nullable = false)
    private int reorderLevel;

    private boolean lowStockAlert;

    @Column(nullable = false)
    private LocalDateTime lastUpdated = LocalDateTime.now();

    @Column(nullable = false)
    private String location;

    private String barcode;

    private String sku;

    private boolean active;
}
