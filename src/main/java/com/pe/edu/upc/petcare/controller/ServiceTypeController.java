package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Customer;
import com.pe.edu.upc.petcare.model.ServiceType;
import com.pe.edu.upc.petcare.resource.CustomerResource;
import com.pe.edu.upc.petcare.resource.SaveCustomerResource;
import com.pe.edu.upc.petcare.resource.SaveServiceTypeResource;
import com.pe.edu.upc.petcare.resource.ServiceTypeResource;
import com.pe.edu.upc.petcare.service.ServiceTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ServiceTypeController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ServiceTypeService serviceTypeService;

    @GetMapping("/servicesType")
    public Page<ServiceTypeResource> getAllServiceType(Pageable pageable)
    {
        List<ServiceTypeResource> serviceType = serviceTypeService.getAllServiceType(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int serviceTypeCount = serviceType.size();
        return new PageImpl<>(serviceType,pageable,serviceTypeCount);
    }

    @GetMapping("/servicesType/{id}")
    public ServiceTypeResource getServiceTypeById(@PathVariable(name = "id")Long serviceTypeId){
        return convertToResource(serviceTypeService.getServiceTypeById(serviceTypeId));
    }

    @PostMapping("/servicesType")
    public ServiceTypeResource createServiceType (@Valid @RequestBody SaveServiceTypeResource resource){
        return convertToResource(serviceTypeService.createServiceType(convertToEntity(resource)));
    }

    @PutMapping("/servicesType/{id}")
    public ServiceTypeResource updateServiceType (@PathVariable(name = "id")Long serviceTypeId,
                                           @Valid @RequestBody SaveServiceTypeResource resource){
        return convertToResource(serviceTypeService.updateServiceType(serviceTypeId,convertToEntity(resource)));
    }

    @DeleteMapping("/servicesType/{id}")
    public ResponseEntity<?> deleteServiceType (@PathVariable(name = "id") Long serviceTypeId){
        return serviceTypeService.deleteServiceType(serviceTypeId);
    }


    private ServiceType convertToEntity(SaveServiceTypeResource resource) {
        return mapper.map(resource, ServiceType.class);
    }

    private ServiceTypeResource convertToResource(ServiceType entity) {
        return mapper.map(entity, ServiceTypeResource.class);
    }

}
