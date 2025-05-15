package com.shahriar.ProductService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_PRICE")
    private Long productPrice;

    @Column(name = "PRODUCT_QUANTITY")
    private Long productQuantity;
}
