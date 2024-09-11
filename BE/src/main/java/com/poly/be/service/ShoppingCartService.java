package com.poly.be.service;

import com.poly.be.model.Cart;
import com.poly.be.model.CartItem;
import com.poly.be.model.Product;
import com.poly.be.repository.CartItemRepository;
import com.poly.be.repository.CartRepository;
import com.poly.be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    public List<Cart> getAll(){
        return cartRepository.findAll();
    }
    public CartItem addToCart(int productId, int quantity) {
        Optional<Cart> cartOptional = cartRepository.findByUser(null);
        Optional<Product> productOptional = productRepository.findById(productId);
        if (cartOptional.isPresent() && productOptional.isPresent()) {
            Cart cart = cartOptional.get();
            Product product = productOptional.get();
            Optional<CartItem> existingCartItemOptional = cartItemRepository.findByCartAndProduct(cart, product);
            if (existingCartItemOptional.isPresent()) {
                CartItem existingCartItem = existingCartItemOptional.get();
                existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
                existingCartItem.setTotalAmount(existingCartItem.getQuantity() * product.getPrice());

                return cartItemRepository.save(existingCartItem);
            } else {
                CartItem cartItem = new CartItem();
                cartItem.setCart(cart);
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartItem.setPrice(product.getPrice());
                cartItem.setTotalAmount(quantity * product.getPrice());
                return cartItemRepository.save(cartItem);
            }
        } else {
            Cart newCart = new Cart();
            newCart.setCreatedAt(new Date());
            newCart = cartRepository.save(newCart);
            Product product = productOptional.orElseThrow(() -> new RuntimeException("Product not found"));
            CartItem cartItem = new CartItem();
            newCart.setCreatedAt(new Date());
            newCart.setUpdatedAt(new Date());
            cartItem.setCart(newCart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setPrice(product.getPrice());
            System.out.println("price : " + product.getPrice());
            cartItem.setTotalAmount(quantity * product.getPrice());
            return cartItemRepository.save(cartItem);
        }
    }

    public void removeFromCart(int cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
