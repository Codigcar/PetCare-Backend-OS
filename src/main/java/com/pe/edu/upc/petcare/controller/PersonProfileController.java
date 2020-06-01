package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.resource.PersonProfileResource;
import com.pe.edu.upc.petcare.resource.save.SavePersonProfileResource;
import com.pe.edu.upc.petcare.service.PersonProfileService;

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
public class PersonProfileController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PersonProfileService personProfileService;


    @GetMapping("/people")
    public Page<PersonProfileResource> getAllCustomers(Pageable pageable){
        List<PersonProfileResource> customers = personProfileService.getAllCustomers(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int customerCount = customers.size();
        return new PageImpl<>(customers,pageable,customerCount);
    }

    @GetMapping("/people/{id}")
    public PersonProfileResource getCustomerById(@PathVariable(name = "id")Long peopleId){
        return convertToResource(personProfileService.getCustomerById(peopleId));
    }

    @PostMapping("/people")
    public PersonProfileResource createCustomer(@Valid @RequestBody SavePersonProfileResource resource){
        return convertToResource(personProfileService.createCustomer(convertToEntity(resource)));
    }

    @PutMapping("/people/{id}")
    public PersonProfileResource updateCustomer(@PathVariable(name = "id")Long peopleId,
                                                @Valid @RequestBody SavePersonProfileResource resource){
        return convertToResource(personProfileService.updateCustomer(peopleId,convertToEntity(resource)));
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") Long peopleId){
        return personProfileService.deleteCustomer(peopleId);
    }

    private PersonProfile convertToEntity(SavePersonProfileResource resource) {
        return mapper.map(resource, PersonProfile.class);
    }

    private PersonProfileResource convertToResource(PersonProfile entity) {
        return mapper.map(entity, PersonProfileResource.class);
    }

}
