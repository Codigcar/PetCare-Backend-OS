package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    Page<Product> getAllByProviderJoinTypeProductId (Long providerJoinTypeProductId,Pageable pageable);
     Product createProduct(Long providerJoinTypeProductId,Product product);
    Product updateProduct(Long providerJoinTypeProductId,Long productId,Product productDetails);
    ResponseEntity<?> deleteProduct(Long providerJoinTypeProductId,Long productId);
}
