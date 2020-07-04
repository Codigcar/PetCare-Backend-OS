package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductTypeService {
    Page<ProductType> getAllServiceType(Pageable pageable);
    ProductType getServiceTypeById(Long serviceTypeId);
    ProductType createServiceType(ProductType productType);
    ProductType updateServiceType(Long serviceTypeId, ProductType productTypeRequest);
    ResponseEntity<?> deleteServiceType(Long serviceTypeId);

}
