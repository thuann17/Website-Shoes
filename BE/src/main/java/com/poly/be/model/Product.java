package com.poly.be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name= "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private String imageUrl;
    private String desciption;
    private String status;

    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetail;

    @OneToMany(mappedBy = "product")
    private  List<CartItem> cartItems;

    @OneToMany(mappedBy = "products")
    private  List<OrderItem> orderItems;
}
