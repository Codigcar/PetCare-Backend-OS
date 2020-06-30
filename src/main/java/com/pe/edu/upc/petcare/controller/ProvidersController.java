package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.resource.ProductResource;
import com.pe.edu.upc.petcare.resource.ProviderResource;
import com.pe.edu.upc.petcare.resource.save.SaveProviderResource;
import com.pe.edu.upc.petcare.service.ProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.domain.Page;
import javax.validation.Valid;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/business/{businessId}/providers")
public class ProvidersController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProviderService providerService;

    @GetMapping
    public ResponseEntity<List<ProviderResource>> getAllProviders(Pageable pageable){
        List<Provider> providers = providerService.getAllProviders(pageable);
        List<ProviderResource> providerResources = providers.stream().map(this::convertToResource).collect(Collectors.toList());
        return ResponseEntity.ok(providerResources);
    }

    @GetMapping("/{providerId}")
    public ProviderResource getProviderById(@PathVariable(name = "providerId")Long providerId){
        return convertToResource(providerService.getProviderById(providerId));
    }

    @PostMapping
    public ProviderResource createProvider(@Valid @RequestBody SaveProviderResource resource){
        Provider provider=convertToEntity(resource);
        return convertToResource(providerService.createProvider(provider));
    }

    @PutMapping("/{providerId}")
    public ProviderResource updateProvider( @PathVariable("businessId") Long businessId,
                                            @PathVariable(name = "providerId")Long providerId,
                                            @Valid @RequestBody SaveProviderResource resource){
        Provider provider=convertToEntity(resource);
        return convertToResource(providerService.updateProvider(businessId, providerId,provider));
    }

    @DeleteMapping("/{providerId}")
    public ResponseEntity<?> deleteProvider(@PathVariable(name ="providerId")Long providerId){
        return providerService.deleteProvider(providerId);
    }

    private Provider convertToEntity(SaveProviderResource resource) {

        return mapper.map(resource, Provider.class);
    }

    private ProviderResource convertToResource(Provider entity) {

        return mapper.map(entity, ProviderResource.class);
    }

}
