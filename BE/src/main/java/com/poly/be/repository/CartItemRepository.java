package com.poly.be.repository;

import com.poly.be.model.Cart;
import com.poly.be.model.CartItem;
import com.poly.be.model.Product;
import com.poly.be.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findByCartAndProductDetail(Cart cart, ProductDetail productDetail);
    void deleteByCart_CartId(int cartId);
}
