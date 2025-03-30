package com.effigoproject.spring_batch.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Auto-generate batchId
    private String batchId;
    private String id;
    private String name;
    private String language;
    @Column(length = 1000)
    private String bio;
    private double version;
}