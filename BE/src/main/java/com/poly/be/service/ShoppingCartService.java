package com.poly.be.service;

import com.poly.be.model.Cart;
import com.poly.be.model.CartItem;
import com.poly.be.model.ProductDetail;
import com.poly.be.repository.CartItemRepository;
import com.poly.be.repository.CartRepository;
import com.poly.be.repository.ProductDetailRepository;
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
    @Autowired
    private ProductDetailRepository productDetailRepository;

    public List<Cart> getAll(){
        return cartRepository.findAll();
    }
    public CartItem addToCart(int productDetailId, int quantity) {
        Optional<Cart> cartOptional = cartRepository.findByUser(null);
        Optional<ProductDetail> productOptional = productDetailRepository.findById(productDetailId);
        if (cartOptional.isPresent() && productOptional.isPresent()) {
            Cart cart = cartOptional.get();
            ProductDetail productDetail = productOptional.get();
            Optional<CartItem> existingCartItemOptional = cartItemRepository.findByCartAndProductDetail(cart, productDetail);
            if (existingCartItemOptional.isPresent()) {
                CartItem existingCartItem = existingCartItemOptional.get();
                existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
                existingCartItem.setTotalAmount(existingCartItem.getQuantity() * productDetail.getPrice());

                return cartItemRepository.save(existingCartItem);
            } else {
                CartItem cartItem = new CartItem();
                cartItem.setCart(cart);
                cartItem.setProductDetail(productDetail);
                cartItem.setQuantity(quantity);
                cartItem.setPrice(productDetail.getPrice());
                cartItem.setTotalAmount(quantity * productDetail.getPrice());
                return cartItemRepository.save(cartItem);
            }
        } else {
            Cart newCart = new Cart();
            newCart.setCreatedAt(new Date());
            newCart = cartRepository.save(newCart);
            ProductDetail product = productOptional.orElseThrow(() -> new RuntimeException("Product not found"));
            CartItem cartItem = new CartItem();
            newCart.setCreatedAt(new Date());
            newCart.setUpdatedAt(new Date());
            cartItem.setCart(newCart);
            cartItem.setProductDetail(product);
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
