package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Affiliation;
import com.pe.edu.upc.petcare.model.BusinessProfile;
import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.resource.AffiliationResource;
import com.pe.edu.upc.petcare.resource.BusinessProfileResource;
import com.pe.edu.upc.petcare.resource.save.SaveAffiliationResource;
import com.pe.edu.upc.petcare.resource.save.SaveBusinessProfileResource;
import com.pe.edu.upc.petcare.service.AffiliationService;
import com.pe.edu.upc.petcare.service.BusinessProfileService;
import com.pe.edu.upc.petcare.service.PersonProfileService;
import com.pe.edu.upc.petcare.service.ProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/people/{personId}/providers/{providerId}/affiliations")
public class PersonAffiliateController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AffiliationService affiliationService;

    @Autowired
    private PersonProfileService personProfileService;

    @Autowired
    private ProviderService providerService;

    @PostMapping
    public AffiliationResource create(  @PathVariable("personId") Long personId,
                                        @PathVariable("providerId") Long providerId,
                                            @RequestBody SaveAffiliationResource resource){

        return convertToResource(affiliationService.create(personId,providerId,convertToEntity(resource)));
    }

    private Affiliation convertToEntity(SaveAffiliationResource resource) {
        return mapper.map(resource, Affiliation.class);
    }

    private AffiliationResource convertToResource(Affiliation entity) {
        return mapper.map(entity, AffiliationResource.class);
    }
    //

}
