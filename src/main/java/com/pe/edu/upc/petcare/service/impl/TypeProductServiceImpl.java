package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.TypeProduct;
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
    public Page<TypeProduct> getAllServiceType(Pageable pageable) {
        return typeProductRepository.findAll(pageable);
    }

    @Override
    public TypeProduct getServiceTypeById(Long serviceTypeId) {
        return typeProductRepository.findById(serviceTypeId)
                .orElseThrow(()->new ResourceNotFoundException("ServiceType","Id",serviceTypeId));
    }

    @Override
    public TypeProduct createServiceType(TypeProduct typeProduct) {
        return typeProductRepository.save(typeProduct);
    }

    @Override
    public TypeProduct updateServiceType(Long serviceTypeId, TypeProduct typeProductRequest) {
        TypeProduct typeProduct = typeProductRepository.findById(serviceTypeId)
                .orElseThrow(()->new ResourceNotFoundException("ServiceType","Id",serviceTypeId));
        typeProduct.setName(typeProductRequest.getName());

        return typeProductRepository.save(typeProduct);
    }

    @Override
    public ResponseEntity<?> deleteServiceType(Long serviceTypeId) {
        TypeProduct typeProduct = typeProductRepository.findById(serviceTypeId)
                .orElseThrow(()->new ResourceNotFoundException("ServiceType","Id",serviceTypeId));
        typeProductRepository.delete(typeProduct);

        return ResponseEntity.ok().build();
    }
}
