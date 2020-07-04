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
    public Page<Product> getAllByProviderJoinTypeProductId(Long providerJoinTypeProductId, Pageable pageable) {
        return productRepository.findAllByProviderJoinProductTypeId(providerJoinTypeProductId,pageable);
    }

    @Override
    public Product createProduct(Long joinId,Product product) {
        return providerJoinProductTypeRepository.findById(joinId).map(providerJoinTypeProduct -> {
            product.setProviderJoinProductType(providerJoinTypeProduct);
            return productRepository.save(product);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Provider Join TypeProduct" + "Id" + joinId));
    }

    @Override
    public Product updateProduct(Long providerJoinTypeProductId,Long productId, Product productDetails) {
        if(!providerJoinProductTypeRepository.existsById(providerJoinTypeProductId))
            throw new ResourceNotFoundException("ProviderJoinProduct","Id",providerJoinTypeProductId);

        return productRepository.findById(productId).map(product -> {
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            return productRepository.save(product);
        }).orElseThrow(()->new ResourceNotFoundException("Product","Id",productId));
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long providerJoinTypeProductId,Long productId) {
        return productRepository.findByIdAndProviderJoinProductTypeId(providerJoinTypeProductId,productId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Product not found with Id"+productId+"and ProviderJoinProductId"+providerJoinTypeProductId));
    }
}
