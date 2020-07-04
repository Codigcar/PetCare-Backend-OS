package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.BusinessProfile;
import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.resource.BusinessProfileResource;
import com.pe.edu.upc.petcare.resource.ProviderResource;
import com.pe.edu.upc.petcare.service.ProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/providers")
public class AdminProvider {
    @Autowired
    ModelMapper mapper;
    @Autowired
    ProviderService providerService;

    @GetMapping
    public ResponseEntity<List<ProviderResource>> getAllProviders(Pageable pageable){
        List<Provider> providers = providerService.getAllProviders(pageable);
        List<ProviderResource> providerResources = providers.stream().map(this::convertToResource).collect(Collectors.toList());
        return ResponseEntity.ok(providerResources);
    }

    private ProviderResource convertToResource(Provider entity) {
        return mapper.map(entity, ProviderResource.class);
    }
}
