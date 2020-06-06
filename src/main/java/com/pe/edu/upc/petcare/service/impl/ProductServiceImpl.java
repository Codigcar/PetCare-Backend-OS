package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.Product;
import com.pe.edu.upc.petcare.repository.ProductRepository;
import com.pe.edu.upc.petcare.repository.ProviderJoinProductTypeRepository;
import com.pe.edu.upc.petcare.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProviderJoinProductTypeRepository providerJoinProductTypeRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAllByProviderJoinTypeProductId(Long productTypeId, Pageable pageable) {
        return productRepository.findAllByProviderJoinProductTypeId(productTypeId,pageable);
    }

    @Override
    public Product createProduct(Long productTypeId,Product product) {
        return providerJoinProductTypeRepository.findById(productTypeId).map(providerJoinTypeProduct -> {
            product.setProviderJoinProductType(providerJoinTypeProduct);
            return productRepository.save(product);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Product Type" + "Id" + productTypeId));
    }

    @Override
    public Product updateProduct(Long productTypeId,Long productId, Product productDetails) {
        if(!providerJoinProductTypeRepository.existsById(productTypeId))
            throw new ResourceNotFoundException("ProviderJoinProduct","Id",productTypeId);

        return productRepository.findById(productId).map(product -> {
            product.setName(productDetails.getName());

            return productRepository.save(product);
        }).orElseThrow(()->new ResourceNotFoundException("Product","Id",productId));
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long productTypeId,Long productId) {
        return productRepository.findByIdAndProviderJoinProductTypeId(productTypeId,productId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Product not found with Id"+productId+"and ProviderJoinProductId"+productTypeId));
    }
}
