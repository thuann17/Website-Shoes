package com.poly.be.service;

import com.poly.be.model.Product;
import com.poly.be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(int productId,Product productDetails) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setProductName(productDetails.getProductName());
            product.setQuantity(productDetails.getQuantity());
            product.setImageUrl(productDetails.getImageUrl());
            product.setPrice(productDetails.getPrice());
            product.setDesciption(productDetails.getDesciption());
            product.setStatus(productDetails.getStatus());
            return productRepository.save(product);
        }return  null;
    }
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }
}
