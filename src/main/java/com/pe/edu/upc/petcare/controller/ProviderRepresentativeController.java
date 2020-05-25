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
    private ProviderRepresentativeService providerrepresentativeService;

    @GetMapping("/provider/{providerId}/providerrepresentative")
    public Page<ProviderRepresentativeResource> getAllProviderRepresentativesByProviderId
            (@PathVariable(name = "providerId")Long providerId, Pageable pageable){
        Page<ProviderRepresentative> providerrepresentativePage=providerrepresentativeService.
                getAllProviderRepresentativesByProviderId(providerId,pageable);
        List<ProviderRepresentativeResource>  resources=providerrepresentativePage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @GetMapping("/provider/{providerId}/providerrepresentative/{providerrepresentativeId}")
    public ProviderRepresentativeResource getProviderRepresentativeByIdAndProviderId
            (@PathVariable(name = "providerId")Long providerId,
                                               @PathVariable(name = "providerrepresentativeId")
                                                       Long providerrepresentativeId){
        return convertToResource(providerrepresentativeService.getProviderRepresentativeByIdAndProviderId
                (providerId,providerrepresentativeId));
    }


    @PostMapping("provider/{providerId}/providerrepresentative")
    public ProviderRepresentativeResource createProviderRepresentative
            (@PathVariable(name = "providerId")Long providerId,
                                 @Valid @RequestBody SaveProviderRepresentativeResource resource){
        return convertToResource(providerrepresentativeService.createProviderRepresentative(providerId,convertToEntity(resource)));
    }

    @PutMapping("/provider/{providerId}/providerrepresentative/{providerrepresentativeId}")
    public ProviderRepresentativeResource updateProviderRepresentative
            (@PathVariable(name = "providerId")Long providerId,
                                 @PathVariable(name = "providerrepresentativeId")Long providerrepsentativeId,
                                 @Valid @RequestBody SaveProviderRepresentativeResource resource){
        return convertToResource(providerrepresentativeService.updateProviderRepresentative
                (providerId,providerrepsentativeId,convertToEntity(resource)));
    }

    @DeleteMapping("/provider/{providerId}/providerrepresentative/{providerrepresentativeId}")
    public ResponseEntity<?> deleteProviderRepresentative(@PathVariable(name = "providerId")Long providerId,
                                       @PathVariable(name = "providerrepresentativeId")Long providerrepresentativeId){
        return providerrepresentativeService.deleteProviderRepresentative(providerId,providerrepresentativeId);
    }

    private ProviderRepresentative convertToEntity(SaveProviderRepresentativeResource resource) {
        return mapper.map(resource, ProviderRepresentative.class);
    }

    private ProviderRepresentativeResource convertToResource(ProviderRepresentative entity) {
        return mapper.map(entity, ProviderRepresentativeResource.class);
    }
}
