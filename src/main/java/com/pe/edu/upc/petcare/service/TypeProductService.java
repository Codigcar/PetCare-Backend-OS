package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.TypeProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TypeProductService {
    Page<TypeProduct> getAllServiceType(Pageable pageable);
    TypeProduct getServiceTypeById(Long serviceTypeId);
    TypeProduct createServiceType(TypeProduct typeProduct);
    TypeProduct updateServiceType(Long serviceTypeId, TypeProduct typeProductRequest);
    ResponseEntity<?> deleteServiceType(Long serviceTypeId);

}
