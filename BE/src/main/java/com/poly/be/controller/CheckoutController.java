package com.poly.be.controller;

import com.poly.be.model.CartItem;
import com.poly.be.model.Order;
import com.poly.be.repository.CartItemRepository;
import com.poly.be.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CheckoutController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartItemRepository cartItemRepository;

    @GetMapping("/checkout")
    public String getFormCheckout(Model model) {
        List<CartItem> cartItems = cartItemRepository.findAll();
        model.addAttribute("cartItems", cartItems);
        return "checkout";
    }

    @GetMapping("/success")
    public String getSuccess() {
        return "success";
    }

    @PostMapping("/checkout/save")
    public String saveCheckout(@RequestParam int cartId,
                               @RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String phone,
                               @RequestParam String email,
                               @RequestParam String city,
                               @RequestParam String province,
                               @RequestParam String country,
                               @RequestParam String specificAddress,
                               Model model) {
        try {
            Order order = orderService.addOrder(cartId, firstName, lastName, phone, email, city, province, country, specificAddress);
            if (order != null) {
                return "redirect:/success";
            } else {
                model.addAttribute("errorMessage", "Order could not be created. Please try again.");
                return "checkout"; // Show the same form with an error message
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
            return "checkout"; // Show the same form with an error message
        }
    }

}
