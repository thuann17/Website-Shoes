package com.poly.be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Cart_Items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int cartItemId;
    int quantity;
    double price;
    double totalAmount;

    @ManyToOne
    @JoinColumn(name = "cartId")
    Cart cart;

    @ManyToOne
    @JoinColumn(name = "productDetailId")
    ProductDetail productDetail;
}
