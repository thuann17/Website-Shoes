package com.poly.be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int orderId;
    Date createAt;
    Date updateAt;
    String status;
    private String firstName;
    private String lastName;
    private String phone;
    private String city;
    private String province;
    private String country;
    private String specificAddress;
    @ManyToOne
    @JoinColumn(name = "username")
    User user;

    @OneToMany(mappedBy = "orders")
    List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "cart")
    Cart carts;
}
