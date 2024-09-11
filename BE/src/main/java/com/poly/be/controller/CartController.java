package com.poly.be.controller;

import com.poly.be.model.CartItem;
import com.poly.be.repository.CartItemRepository;
import com.poly.be.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/cart")
    public String getAllCart(Model model) {
        List<CartItem> cartItems = cartItemRepository.findAll();
        model.addAttribute("cartItems", cartItems);
        return "shopping";
    }

    @PostMapping("/cart/delete/{id}")
    public String deleteCartItem(@PathVariable int id) {
        try {
          shoppingCartService.removeFromCart(id);
            return "redirect:/cart";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }
}
