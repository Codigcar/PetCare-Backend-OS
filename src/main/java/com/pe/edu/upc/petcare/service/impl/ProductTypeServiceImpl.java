package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.ProductType;
import com.pe.edu.upc.petcare.repository.TypeProductRepository;
import com.pe.edu.upc.petcare.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TypeProductServiceImpl implements TypeProductService {
    @Autowired
    TypeProductRepository typeProductRepository;

    @Override
    public Page<ProductType> getAllServiceType(Pageable pageable) {
        return typeProductRepository.findAll(pageable);
    }

    @Override
    public ProductType getServiceTypeById(Long serviceTypeId) {
        return typeProductRepository.findById(serviceTypeId)
                .orElseThrow(()->new ResourceNotFoundException("ServiceType","Id",serviceTypeId));
    }

    @Override
    public ProductType createServiceType(ProductType productType) {
        return typeProductRepository.save(productType);
    }

    @Override
    public ProductType updateServiceType(Long serviceTypeId, ProductType productTypeRequest) {
        ProductType productType = typeProductRepository.findById(serviceTypeId)
                .orElseThrow(()->new ResourceNotFoundException("ServiceType","Id",serviceTypeId));
        productType.setName(productTypeRequest.getName());

        return typeProductRepository.save(productType);
    }

    @Override
    public ResponseEntity<?> deleteServiceType(Long serviceTypeId) {
        ProductType productType = typeProductRepository.findById(serviceTypeId)
                .orElseThrow(()->new ResourceNotFoundException("ServiceType","Id",serviceTypeId));
        typeProductRepository.delete(productType);

        return ResponseEntity.ok().build();
    }
}
