package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.model.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ServiceTypeService {
    Page<ServiceType> getAllServiceType(Pageable pageable);
    ServiceType getServiceTypeById(Long serviceTypeId);
    ServiceType createServiceType(ServiceType serviceType);
    ServiceType updateServiceType(Long serviceTypeId,ServiceType serviceTypeRequest);
    ResponseEntity<?> deleteServiceType(Long serviceTypeId);

}
