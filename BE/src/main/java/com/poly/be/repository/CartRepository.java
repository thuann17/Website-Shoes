package com.poly.be.repository;

import com.poly.be.model.Cart;
import com.poly.be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser(User username);

    void deleteByCartId(int cartId);
}
