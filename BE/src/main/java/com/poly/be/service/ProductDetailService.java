package com.poly.be.service;

import com.poly.be.model.ProductDetail;
import com.poly.be.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    public List<ProductDetail> getProductDetailAll() {
        return productDetailRepository.findAll();
    }
    public ProductDetail getProductDetailById(int productDetaiId) {
        return productDetailRepository.findById(productDetaiId).orElse(null);
    }
    public ProductDetail saveProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }
    public ProductDetail updateProductDetail(ProductDetail productDetail) {

        return productDetailRepository.save(productDetail);
    }
}
