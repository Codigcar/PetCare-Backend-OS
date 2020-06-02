package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.TypeProduct;
import com.pe.edu.upc.petcare.resource.save.SaveTypeProductResource;
import com.pe.edu.upc.petcare.resource.TypeProductResource;
import com.pe.edu.upc.petcare.service.TypeProductService;
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
public class TypeProductController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private TypeProductService typeProductService;

    @GetMapping("/typeProducts")
    public Page<TypeProductResource> getAllServiceType(Pageable pageable)
    {
        List<TypeProductResource> serviceType = typeProductService.getAllServiceType(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int serviceTypeCount = serviceType.size();
        return new PageImpl<>(serviceType,pageable,serviceTypeCount);
    }

    @GetMapping("/typeProducts/{id}")
    public TypeProductResource getServiceTypeById(@PathVariable(name = "id")Long serviceTypeId){
        return convertToResource(typeProductService.getServiceTypeById(serviceTypeId));
    }

    @PostMapping("/typeProducts")
    public TypeProductResource createServiceType (@Valid @RequestBody SaveTypeProductResource resource){
        return convertToResource(typeProductService.createServiceType(convertToEntity(resource)));
    }

    @PutMapping("/typeProducts/{id}")
    public TypeProductResource updateServiceType (@PathVariable(name = "id")Long serviceTypeId,
                                                  @Valid @RequestBody SaveTypeProductResource resource){
        return convertToResource(typeProductService.updateServiceType(serviceTypeId,convertToEntity(resource)));
    }

    @DeleteMapping("/typeProducts/{id}")
    public ResponseEntity<?> deleteServiceType (@PathVariable(name = "id") Long serviceTypeId){
        return typeProductService.deleteServiceType(serviceTypeId);
    }


    private TypeProduct convertToEntity(SaveTypeProductResource resource) {
        return mapper.map(resource, TypeProduct.class);
    }

    private TypeProductResource convertToResource(TypeProduct entity) {
        return mapper.map(entity, TypeProductResource.class);
    }

}
