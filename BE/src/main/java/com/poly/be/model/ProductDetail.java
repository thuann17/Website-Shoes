package com.poly.be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Product_Detais")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int productDetailId;
    String status;
    double price;
    int quantity;
    String image_url;

    @ManyToOne
    @JoinColumn(name = "productId")
    Product product;

    @OneToMany(mappedBy = "productDetail")
    List<CartItem> cartItems;

    @OneToMany(mappedBy = "productDetail")
    List<OrderItem> orderItems;
}
