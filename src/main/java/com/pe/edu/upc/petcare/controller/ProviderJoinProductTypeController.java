package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.ProviderJoinProductType;
import com.pe.edu.upc.petcare.resource.ProviderJoinProductResource;
import com.pe.edu.upc.petcare.resource.save.SaveProviderJoinProductResource;
import com.pe.edu.upc.petcare.service.ProviderJoinProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/business/{businessId}/provider/{providerId}/products-types")
@RestController
public class ProviderJoinProductTypeController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProviderJoinProductService providerJoinProductService;

    @PostMapping("/{productId}")
    public ProviderJoinProductResource createRelationship(@PathVariable(name = "providerId")Long providerId,
                                                          @PathVariable(name = "productId")Long productId,
                                                          @Valid @RequestBody SaveProviderJoinProductResource resource){
        return convertToResource(providerJoinProductService.createRelationship(providerId,productId,convertToEntity(resource)));
    }

    private ProviderJoinProductType convertToEntity(SaveProviderJoinProductResource resource) {
        return mapper.map(resource, ProviderJoinProductType.class);
    }

    private ProviderJoinProductResource convertToResource(ProviderJoinProductType entity) {
        return mapper.map(entity, ProviderJoinProductResource.class);
    }

}
