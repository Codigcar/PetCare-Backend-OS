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
@RequestMapping("/api/people")
public class PeopleAccountsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PersonProfileService personProfileService;


    @GetMapping
    public ResponseEntity<List<PersonProfileResource>> getAllPeople(Pageable pageable){
        List<PersonProfile> personProfiles = personProfileService.getAllCustomers();
        List<PersonProfileResource>personProfileResources = personProfiles.stream().map(this::convertToResource).collect(Collectors.toList());
        return ResponseEntity.ok(personProfileResources);
    }

    //Get PersonProfile by Person ID
    @GetMapping("/{id}")
    public PersonProfileResource getPersonById(@PathVariable(name = "id")Long peopleId){
        return convertToResource(personProfileService.getPersonById(peopleId));
    }

    @PostMapping
    public PersonProfileResource createCustomer(@Valid @RequestBody SavePersonProfileResource resource){
        return convertToResource(personProfileService.createCustomer(convertToEntity(resource)));
    }

    @PutMapping("/{id}")
    public PersonProfileResource updateCustomer(@PathVariable(name = "id")Long peopleId,
                                                @Valid @RequestBody SavePersonProfileResource resource){
        return convertToResource(personProfileService.updateCustomer(peopleId,convertToEntity(resource)));
    }

    @DeleteMapping("/{id}")
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
