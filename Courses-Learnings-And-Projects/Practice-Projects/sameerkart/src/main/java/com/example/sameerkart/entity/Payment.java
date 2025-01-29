package com.example.sameerkart.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String paymentId;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private String paymentMethod; // "CARD", "PAYPAL", "COD"

    @Column(nullable = false)
    private String paymentStatus; // "PENDING", "COMPLETED", "FAILED"

    private String transactionId;
}