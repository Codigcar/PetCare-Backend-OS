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
@RequestMapping("/api")
public class PetController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PetService petService;

    @GetMapping("/people/{peopleId}/pets")
    public Page<PetResource> getAllPetsByCustomerId(@PathVariable(name = "peopleId")Long peopleId,
                                                    Pageable pageable){
        Page<Pet> petPage=petService.getAllPetsByPersonProfileId(peopleId,pageable);
        List<PetResource>  resources=petPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @GetMapping("/people/{peopleId}/pets/{petId}")
    public PetResource getPetByIdAndCustomerId(@PathVariable(name = "peopleId")Long peopleId,
                                               @PathVariable(name = "petId")Long petId){
        return convertToResource(petService.getPetByIdAndPersonProfileId(peopleId,petId));
    }


    @PostMapping("/people/{peopleId}/pets")
    public PetResource createPet(@PathVariable(name = "peopleId")Long peopleId,
                                 @Valid @RequestBody SavePetResource resource){
        return convertToResource(petService.createPet(peopleId,convertToEntity(resource)));
    }

    @PutMapping("/people/{peopleId}/pets/{petId}")
    public PetResource updatePet(@PathVariable(name = "peopleId")Long peopleId,
                                 @PathVariable(name = "petId")Long petId,
                                 @Valid @RequestBody SavePetResource resource){
        return convertToResource(petService.updatePet(peopleId,petId,convertToEntity(resource)));
    }

    @DeleteMapping("people/{peopleId}/pets/{petId}")
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