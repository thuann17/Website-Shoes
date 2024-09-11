//package com.poly.be.controller;
//
//import com.poly.be.model.CartItem;
//import com.poly.be.service.ShoppingCartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/cart")
//public class CartRestController {
//    @Autowired
//    private ShoppingCartService shoppingCartService;
//
//    @PostMapping("")
//    public CartItem addItemToCart(@RequestBody int cartId, @RequestBody CartItem cartItem, @RequestParam int productId) {
//        return shoppingCartService.addToCart(cartItem, cartId, productId);
//    }
//}
