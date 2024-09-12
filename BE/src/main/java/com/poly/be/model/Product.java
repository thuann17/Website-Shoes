package com.poly.be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int productId;
    String productName;
    String desciption;
    String status;

    @OneToMany(mappedBy = "product")
     List<ProductDetail> productDetail;

}
