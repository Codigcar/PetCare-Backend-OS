package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.model.ServiceType;
import com.pe.edu.upc.petcare.repository.ServiceTypeRepository;
import com.pe.edu.upc.petcare.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {
    @Autowired
    ServiceTypeRepository serviceTypeRepository;

    @Override
    public Page<ServiceType> getAllServiceType(Pageable pageable) {
        return serviceTypeRepository.findAll(pageable);
    }

    @Override
    public ServiceType getServiceTypeById(Long serviceTypeId) {
        return serviceTypeRepository.findById(serviceTypeId)
                .orElseThrow(()->new ResourceNotFoundException("ServiceType","Id",serviceTypeId));
    }

    @Override
    public ServiceType createServiceType(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    @Override
    public ServiceType updateServiceType(Long serviceTypeId, ServiceType serviceTypeRequest) {
        ServiceType serviceType=serviceTypeRepository.findById(serviceTypeId)
                .orElseThrow(()->new ResourceNotFoundException("ServiceType","Id",serviceTypeId));
        serviceType.setName(serviceTypeRequest.getName());

        return serviceTypeRepository.save(serviceType);
    }

    @Override
    public ResponseEntity<?> deleteServiceType(Long serviceTypeId) {
        ServiceType serviceType=serviceTypeRepository.findById(serviceTypeId)
                .orElseThrow(()->new ResourceNotFoundException("ServiceType","Id",serviceTypeId));
        serviceTypeRepository.delete(serviceType);

        return ResponseEntity.ok().build();
    }
}
