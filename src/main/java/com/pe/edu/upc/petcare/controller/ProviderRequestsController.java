package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Payment;
import com.pe.edu.upc.petcare.model.PersonRequest;
import com.pe.edu.upc.petcare.resource.PaymentResource;
import com.pe.edu.upc.petcare.resource.PersonProfileResource;
import com.pe.edu.upc.petcare.resource.PersonRequestResource;
import com.pe.edu.upc.petcare.resource.save.SavePersonRequestResource;
import com.pe.edu.upc.petcare.service.PersonRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/business/{businessId}/providers/{providerId}/products/{productId}/request")
public class ProviderRequestsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PersonRequestService personRequestService;

    @GetMapping
    public ResponseEntity<List<PersonRequestResource>> getAllPersonRequestByProductId(@PathVariable("providerId")Long providerId,
                                                                                      @PathVariable("productId")Long productId){
        List<PersonRequest> personRequests = personRequestService.getAllByProductId(productId);
        List<PersonRequestResource> resources = personRequests.stream().map(this::convertToResource).collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    private PersonRequest convertToEntity(SavePersonRequestResource resource) {
        return mapper.map(resource, PersonRequest.class);
    }

    private PersonRequestResource convertToResource(PersonRequest entity) {
        return mapper.map(entity, PersonRequestResource.class);
    }

}
