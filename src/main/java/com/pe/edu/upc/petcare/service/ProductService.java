package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    Page<Product> getAllByProviderJoinTypeProductId (Long productTypeId,Pageable pageable);
     Product createProduct(Long productTypeId,Product product);
    Product updateProduct(Long productTypeId,Long productId,Product productDetails);
    ResponseEntity<?> deleteProduct(Long productTypeId,Long productId);
}
