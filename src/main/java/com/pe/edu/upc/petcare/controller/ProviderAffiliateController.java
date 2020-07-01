package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Affiliation;
import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.resource.AffiliationResource;
import com.pe.edu.upc.petcare.resource.save.SaveAffiliationResource;
import com.pe.edu.upc.petcare.service.AffiliationService;
import com.pe.edu.upc.petcare.service.PersonProfileService;
import com.pe.edu.upc.petcare.service.ProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/business/{businessId}/providers/{providerId}/affiliations")
public class ProviderAffiliateController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AffiliationService affiliationService;

    @GetMapping
    public ResponseEntity<List<PersonProfile>> getAllByProviderId(
                                      @PathVariable("businessId") Long peopleId,
                                      @PathVariable("providerId")Long providerId,
                                      @RequestBody SaveAffiliationResource resource)
    {
        List<PersonProfile> personProfiles = affiliationService.getAllPersonByProviderId(Provider.builder().id(providerId).build());
        return ResponseEntity.ok(personProfiles);
    }
    /*@GetMapping("/trees/{treeId}/leafs")
    public ResponseEntity<List<GreenLeaf>> getAllLeafsByTreesId(@PathVariable("treeId") Long treeId){
        List<GreenLeaf> leafs = greenLeafService.findByBigTrees(BigTrees.builder().id(treeId).build());
        return ResponseEntity.ok(leafs);
    }*/

    private Affiliation convertToEntity(SaveAffiliationResource resource) {
        return mapper.map(resource, Affiliation.class);
    }

    private AffiliationResource convertToResource(Affiliation entity) {
        return mapper.map(entity, AffiliationResource.class);
    }

}
