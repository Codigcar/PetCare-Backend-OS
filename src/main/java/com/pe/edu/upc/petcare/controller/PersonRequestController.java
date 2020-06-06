package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.BusinessProfile;
import com.pe.edu.upc.petcare.model.PersonRequest;
import com.pe.edu.upc.petcare.resource.BusinessProfileResource;
import com.pe.edu.upc.petcare.resource.PersonRequestResource;
import com.pe.edu.upc.petcare.resource.save.SaveBusinessProfileResource;
import com.pe.edu.upc.petcare.resource.save.SavePersonRequestResource;
import com.pe.edu.upc.petcare.service.BusinessProfileService;
import com.pe.edu.upc.petcare.service.PersonRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/people/{personId}/pets/{petId}/providers/{providerId}/products/{productId}/requests")
public class PersonRequestController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PersonRequestService personRequestService;

    @PostMapping
    public PersonRequestResource create(@PathVariable("personId") Long personId,
                                        @PathVariable("petId") Long petId,
                                        @PathVariable("providerId") Long providerId,
                                        @PathVariable("productId") Long productId,
                                        @RequestBody SavePersonRequestResource resource){
        return convertToResource(personRequestService.create(personId,petId,providerId,productId,convertToEntity(resource)));
    }

    private PersonRequest convertToEntity(SavePersonRequestResource resource) {
        return mapper.map(resource, PersonRequest.class);
    }

    private PersonRequestResource convertToResource(PersonRequest entity) {
        return mapper.map(entity, PersonRequestResource.class);
    }

}
