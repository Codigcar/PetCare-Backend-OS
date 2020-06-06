package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.BusinessProfile;
import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.resource.BusinessProfileResource;
import com.pe.edu.upc.petcare.resource.PersonProfileResource;
import com.pe.edu.upc.petcare.resource.save.SaveBusinessProfileResource;
import com.pe.edu.upc.petcare.resource.save.SavePersonProfileResource;
import com.pe.edu.upc.petcare.service.BusinessProfileService;
import com.pe.edu.upc.petcare.service.PersonProfileService;
import com.pe.edu.upc.petcare.service.ProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/business")
public class BusinessAccountsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BusinessProfileService businessProfileService;

    @PostMapping
    public BusinessProfileResource create( @RequestBody SaveBusinessProfileResource resource){
        return convertToResource(businessProfileService.create(convertToEntity(resource)));
    }

    private BusinessProfile convertToEntity(SaveBusinessProfileResource resource) {
        return mapper.map(resource, BusinessProfile.class);
    }

    private BusinessProfileResource convertToResource(BusinessProfile entity) {
        return mapper.map(entity, BusinessProfileResource.class);
    }

}
