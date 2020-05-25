package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.resource.PetResource;
import com.pe.edu.upc.petcare.resource.SavePetResource;
import com.pe.edu.upc.petcare.service.PetService;

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
public class PetController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PetService petService;

    @GetMapping("/customers/{customerId}/pets")
    public Page<PetResource> getAllPetsByCustomerId(@PathVariable(name = "customerId")Long customerId,
                                                    Pageable pageable){
        Page<Pet> petPage=petService.getAllPetsByCustomerId(customerId,pageable);
        List<PetResource>  resources=petPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @GetMapping("/customers/{customerId}/pets/{petId}")
    public PetResource getPetByIdAndCustomerId(@PathVariable(name = "customerId")Long customerId,
                                               @PathVariable(name = "petId")Long petId){
        return convertToResource(petService.getPetByIdAndCustomerId(customerId,petId));
    }


    @PostMapping("/customers/{customerId}/pets")
    public PetResource createPet(@PathVariable(name = "customerId")Long customerId,
                                 @Valid @RequestBody SavePetResource resource){
        return convertToResource(petService.createPet(customerId,convertToEntity(resource)));
    }

    @PutMapping("/customers/{customerId}/pets/{petId}")
    public PetResource updatePet(@PathVariable(name = "customerId")Long customerId,
                                 @PathVariable(name = "petId")Long petId,
                                 @Valid @RequestBody SavePetResource resource){
        return convertToResource(petService.updatePet(customerId,petId,convertToEntity(resource)));
    }

    @DeleteMapping("customers/{customerId}/pets/{petId}")
    public ResponseEntity<?> deletePet(@PathVariable(name = "customerId")Long customerId,
                                       @PathVariable(name = "petId")Long petId){
        return petService.deletePet(customerId,petId);
    }

    private Pet convertToEntity(SavePetResource resource) {
        return mapper.map(resource, Pet.class);
    }

    private PetResource convertToResource(Pet entity) {
        return mapper.map(entity, PetResource.class);
    }
}