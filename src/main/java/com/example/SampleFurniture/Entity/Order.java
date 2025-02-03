package com.example.SampleFurniture.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true) // Allows null values for custom orders
    @JoinColumn(name = "product_id")
    private Product product;

    private String customProductName;  // For custom orders
    private String customProductDescription;
    private double customProductPrice;

    private int quantity;
    private double totalPrice;
    private LocalDateTime orderDate;
    private String customerName;

    private String orderStatus; // PENDING, PROCESSING, COMPLETED
}