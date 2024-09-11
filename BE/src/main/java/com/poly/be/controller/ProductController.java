package com.poly.be.controller;

import com.poly.be.model.CartItem;
import com.poly.be.model.Product;
import com.poly.be.service.ProductService;
import com.poly.be.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/product")
    public String getAllProduct(Model model) {
        List<Product> product = productService.getAllProduct();
        model.addAttribute("products", product);
        return "product";
    }


    @RequestMapping("/product/add-to-cart")
    public String addToCart(int quantity,
                            @RequestParam int productId, Model model) {
        shoppingCartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }

}
