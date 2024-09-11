package com.poly.be.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Product_Detais")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productDetaiId;
    private String status;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
