package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.ProviderRepresentative;
import com.pe.edu.upc.petcare.resource.ProviderRepresentativeResource;
import com.pe.edu.upc.petcare.resource.SaveProviderRepresentativeResource;
import com.pe.edu.upc.petcare.service.ProviderRepresentativeService;
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
public class ProviderRepresentativeController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProviderRepresentativeService providerRepresentativeService;

    @GetMapping("/provider/{providerId}/provider_representative")
    public Page<ProviderRepresentativeResource> getAllProviderRepresentativesByProviderId
            (@PathVariable(name = "providerId")Long providerId, Pageable pageable){
        Page<ProviderRepresentative> providerRepresentativePage=providerRepresentativeService.
                getAllProviderRepresentativesByProviderId(providerId,pageable);
        List<ProviderRepresentativeResource>  resources=providerRepresentativePage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @GetMapping("/provider/{providerId}/providerRepresentative/{providerRepresentativeId}")
    public ProviderRepresentativeResource getProviderRepresentativeByIdAndProviderId
            (@PathVariable(name = "providerId")Long providerId,
                                               @PathVariable(name = "providerRepresentativeId")
                                                       Long providerRepresentativeId){
        return convertToResource(providerRepresentativeService.getProviderRepresentativeByIdAndProviderId
                (providerId,providerRepresentativeId));
    }


    @PostMapping("provider/{providerId}/providerRepresentative")
    public ProviderRepresentativeResource createProviderRepresentative
            (@PathVariable(name = "providerId")Long providerId,
                                 @Valid @RequestBody SaveProviderRepresentativeResource resource){
        return convertToResource(providerRepresentativeService.createProviderRepresentative(providerId,convertToEntity(resource)));
    }

    @PutMapping("/provider/{providerId}/providerRepresentative/{providerRepresentativeId}")
    public ProviderRepresentativeResource updateProviderRepresentative
            (@PathVariable(name = "providerId")Long providerId,
                                 @PathVariable(name = "providerRepresentativeId")Long providerRepsentativeId,
                                 @Valid @RequestBody SaveProviderRepresentativeResource resource){
        return convertToResource(providerRepresentativeService.updateProviderRepresentative
                (providerId,providerRepsentativeId,convertToEntity(resource)));
    }

    @DeleteMapping("/provider/{providerId}/providerRepresentative/{providerRepresentativeId}")
    public ResponseEntity<?> deleteProviderRepresentative(@PathVariable(name = "providerId")Long providerId,
                                       @PathVariable(name = "providerRepresentativeId")Long providerRepresentativeId){
        return providerRepresentativeService.deleteProviderRepresentative(providerId,providerRepresentativeId);
    }

    private ProviderRepresentative convertToEntity(SaveProviderRepresentativeResource resource) {
        return mapper.map(resource, ProviderRepresentative.class);
    }

    private ProviderRepresentativeResource convertToResource(ProviderRepresentative entity) {
        return mapper.map(entity, ProviderRepresentativeResource.class);
    }
}
