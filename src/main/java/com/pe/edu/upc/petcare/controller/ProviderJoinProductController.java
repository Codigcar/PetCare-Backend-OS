package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.model.ProviderJoinTypeProduct;
import com.pe.edu.upc.petcare.repository.ProviderJoinTypeProductRepository;
import com.pe.edu.upc.petcare.resource.PetResource;
import com.pe.edu.upc.petcare.resource.ProviderJoinProductResource;
import com.pe.edu.upc.petcare.resource.save.SavePetResource;
import com.pe.edu.upc.petcare.resource.save.SaveProviderJoinProductResource;
import com.pe.edu.upc.petcare.service.ProviderJoinProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/business/{businessId}")
@RestController
public class ProviderJoinProductController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProviderJoinProductService providerJoinProductService;

    @PostMapping("/provider/{providerId}/products/{productId}/provider-join-products")
    public ProviderJoinProductResource createRelationship(@PathVariable(name = "providerId")Long providerId,
                                                          @PathVariable(name = "productId")Long productId,
                                                          @Valid @RequestBody SaveProviderJoinProductResource resource){
        return convertToResource(providerJoinProductService.createRelationship(providerId,productId,convertToEntity(resource)));
    }
    private ProviderJoinTypeProduct convertToEntity(SaveProviderJoinProductResource resource) {
        return mapper.map(resource, ProviderJoinTypeProduct.class);
    }

    private ProviderJoinProductResource convertToResource(ProviderJoinTypeProduct entity) {
        return mapper.map(entity, ProviderJoinProductResource.class);
    }

}
