package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.ProductType;
import com.pe.edu.upc.petcare.repository.ProductTypeRepository;
import com.pe.edu.upc.petcare.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    ProductTypeRepository productTypeRepository;

    @Override
    public Page<ProductType> getAllServiceType(Pageable pageable) {
        return productTypeRepository.findAll(pageable);
    }

    @Override
    public ProductType getServiceTypeById(Long serviceTypeId) {
        return productTypeRepository.findById(serviceTypeId)
                .orElseThrow(()->new ResourceNotFoundException("ServiceType","Id",serviceTypeId));
    }

    @Override
    public ProductType createServiceType(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    @Override
    public ProductType updateServiceType(Long serviceTypeId, ProductType productTypeRequest) {
        ProductType productType = productTypeRepository.findById(serviceTypeId)
                .orElseThrow(()->new ResourceNotFoundException("ServiceType","Id",serviceTypeId));
        productType.setName(productTypeRequest.getName());

        return productTypeRepository.save(productType);
    }

    @Override
    public ResponseEntity<?> deleteServiceType(Long serviceTypeId) {
        ProductType productType = productTypeRepository.findById(serviceTypeId)
                .orElseThrow(()->new ResourceNotFoundException("ServiceType","Id",serviceTypeId));
        productTypeRepository.delete(productType);

        return ResponseEntity.ok().build();
    }
}
