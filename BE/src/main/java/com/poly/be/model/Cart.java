package com.poly.be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "Carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int cartId;
    String status;
    Date createdAt;
    Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "username")
    User user;

    @OneToMany(mappedBy = "cart")
    List<CartItem> cartItems;

    @OneToMany(mappedBy = "carts")
    private List<Order> orders;
}
