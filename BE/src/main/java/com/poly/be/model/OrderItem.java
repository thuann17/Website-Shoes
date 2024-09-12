package com.poly.be.model;

import jakarta.persistence.*;
import jakarta.servlet.annotation.HandlesTypes;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int orderItemId;
    int quantity;
    double price;
    double totalAmount;

    @ManyToOne
    @JoinColumn(name = "orderId")
    Order orders;

    @ManyToOne
    @JoinColumn(name="productDetailId")
    ProductDetail productDetail;
}
