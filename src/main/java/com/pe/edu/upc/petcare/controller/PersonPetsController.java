package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.resource.PetResource;
import com.pe.edu.upc.petcare.resource.save.SavePetResource;
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
@RequestMapping("/api/people/{peopleId}/pets")
public class PersonPetsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<PetResource>> getAllPetsByCustomerId(@PathVariable(name = "peopleId")Long peopleId,
                                                    Pageable pageable){
        List<Pet> pets =petService.getAllPetsByPersonProfileId(peopleId,pageable);
        List<PetResource>  resources=pets.stream().map(this::convertToResource).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    //Get Pet By Person Id and Pet Id 
    @GetMapping("/{petId}")
    public PetResource getPetByPeopleId(@PathVariable(name = "peopleId")Long peopleId,
                                        @PathVariable(name = "petId")Long petId){
        return convertToResource(petService.getPetByPeopleId(peopleId,petId));
    }


    @PostMapping
    public PetResource createPet(@PathVariable(name = "peopleId")Long peopleId,
                                 @Valid @RequestBody SavePetResource resource){
        return convertToResource(petService.createPet(peopleId,convertToEntity(resource)));
    }

    @PutMapping("/{petId}")
    public PetResource updatePet(@PathVariable(name = "peopleId")Long peopleId,
                                 @PathVariable(name = "petId")Long petId,
                                 @Valid @RequestBody SavePetResource resource){
        return convertToResource(petService.updatePet(peopleId,petId,convertToEntity(resource)));
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<?> deletePet(@PathVariable(name = "peopleId")Long peopleId,
                                       @PathVariable(name = "petId")Long petId){
        return petService.deletePet(peopleId,petId);
    }

    private Pet convertToEntity(SavePetResource resource) {
        return mapper.map(resource, Pet.class);
    }

    private PetResource convertToResource(Pet entity) {
        return mapper.map(entity, PetResource.class);
    }
}