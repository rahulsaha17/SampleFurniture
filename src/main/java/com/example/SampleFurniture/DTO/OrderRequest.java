package com.example.SampleFurniture.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Long productId;  // For stock orders
    private String customProductName;
    private String customProductDescription;
    private double customProductPrice;
    private int quantity;
    private String customerName;
}
