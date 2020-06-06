package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.Product;
import com.pe.edu.upc.petcare.repository.ProductRepository;
import com.pe.edu.upc.petcare.repository.ProviderJoinTypeProductRepository;
import com.pe.edu.upc.petcare.repository.ProviderRepository;
import com.pe.edu.upc.petcare.resource.ProviderJoinProductResource;
import com.pe.edu.upc.petcare.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProviderJoinTypeProductRepository providerJoinTypeProductRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAllByProviderJoinTypeProductId(Long providerJoinTypeProductId, Pageable pageable) {
        return productRepository.findAllByProviderJoinTypeProductId(providerJoinTypeProductId,pageable);
    }

    @Override
    public Product createProduct(Long providerJoinTypeProductId,Product product) {
        return providerJoinTypeProductRepository.findById(providerJoinTypeProductId).map(providerJoinTypeProduct -> {
            product.setProviderJoinTypeProduct(providerJoinTypeProduct);
            return productRepository.save(product);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Provider Join TypeProduct" + "Id" + providerJoinTypeProductId));
    }

    @Override
    public Product updateProduct(Long providerJoinTypeProductId,Long productId, Product productDetails) {
        if(!providerJoinTypeProductRepository.existsById(providerJoinTypeProductId))
            throw new ResourceNotFoundException("ProviderJoinProduct","Id",providerJoinTypeProductId);

        return productRepository.findById(productId).map(product -> {
            product.setName(productDetails.getName());

            return productRepository.save(product);
        }).orElseThrow(()->new ResourceNotFoundException("Product","Id",productId));
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long providerJoinTypeProductId,Long productId) {
        return productRepository.findByIdAndProviderJoinTypeProductId(providerJoinTypeProductId,productId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Product not found with Id"+productId+"and ProviderJoinProductId"+providerJoinTypeProductId));
    }
}
