package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    Page<Product> getAllProducts (Pageable pageable);
    Page<Product> getAllProductsByProviderId(Long providerId,Pageable pageable);
    Product createProduct(Product product);
    Product updateProduct(Long productId,Product productDetails);
    ResponseEntity<?> deleteProduct(Long productId);
}
