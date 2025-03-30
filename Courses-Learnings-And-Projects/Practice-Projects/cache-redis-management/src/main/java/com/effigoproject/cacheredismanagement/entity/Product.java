package com.effigoproject.cacheredismanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("category")
    private String category;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("weight")
    private Double weight;
    @JsonProperty("color")
    private String color;

    @JsonProperty("available")
    private Boolean available;
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("sku")
    private String sku;
    @JsonProperty("barcode")
    private String barcode;
    @JsonProperty("manufacturer")
    private String manufacturer;
    @JsonProperty("supplier")
    private String supplier;
    @JsonProperty("warrantyPeriod")
    private Integer warrantyPeriod;
    @JsonProperty("discount")
    private Double discount;
    @JsonProperty("isFeatured")
    private Boolean isFeatured;
    @JsonProperty("dimensions")
    private String dimensions;
    @JsonProperty("material")
    private String material;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("additionalInfo")
    private String additionalInfo;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Product(Long id, String name, String description, String category, String brand, BigDecimal price, Integer quantity, Double weight, String color, Boolean available, LocalDateTime createdAt, LocalDateTime updatedAt, String sku, String barcode, String manufacturer, String supplier, Integer warrantyPeriod, Double discount, Boolean isFeatured, String dimensions, String material, String imageUrl, String additionalInfo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
        this.color = color;
        this.available = available;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sku = sku;
        this.barcode = barcode;
        this.manufacturer = manufacturer;
        this.supplier = supplier;
        this.warrantyPeriod = warrantyPeriod;
        this.discount = discount;
        this.isFeatured = isFeatured;
        this.dimensions = dimensions;
        this.material = material;
        this.imageUrl = imageUrl;
        this.additionalInfo = additionalInfo;
    }
    public Product() {}
}
