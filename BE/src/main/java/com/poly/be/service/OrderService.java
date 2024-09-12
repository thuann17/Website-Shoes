package com.poly.be.service;

import com.poly.be.model.Cart;
import com.poly.be.model.CartItem;
import com.poly.be.model.Order;
import com.poly.be.model.OrderItem;
import com.poly.be.repository.CartItemRepository;
import com.poly.be.repository.CartRepository;
import com.poly.be.repository.OrderItemRepository;
import com.poly.be.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public Order addOrder(int cartId, String firstName, String lastName, String phone, String email, String city, String province, String country, String specificAddress) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            Order order = new Order();
            order.setPhone(phone);
            order.setCity(city);
            order.setProvince(province);
            order.setCountry(country);
            order.setSpecificAddress(specificAddress);
            order.setFirstName(firstName);
            order.setLastName(lastName);
            order.setCreateAt(new Date());
            order.setStatus("PENDING");
            order.setUser(null);
            order.setCarts(cart);
            order = orderRepository.save(order);

            List<CartItem> cartItems = cart.getCartItems();
            for (CartItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrders(order);
                orderItem.setProductDetail(cartItem.getProductDetail());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice(cartItem.getPrice());
                orderItemRepository.save(orderItem);

            }
            cartItemRepository.deleteAll();
            return order;

        } else {
            throw new RuntimeException("Cart with ID " + cartId + " not found.");
        }
    }
}
