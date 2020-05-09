package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.resource.PetResource;
import com.pe.edu.upc.petcare.resource.SavePetResource;
import com.pe.edu.upc.petcare.service.PetService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<PetResource>> getAllPets() throws Exception  {
        List<Pet> pets = new ArrayList<>();

        pets = petService.findAll();
        if (pets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<PetResource> resources = pets.stream().map(this::convertToResource).collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResource> getPetById(@PathVariable("id") Long id) throws Exception{
        Pet petDB = petService.findById(id);
        if (petDB == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(convertToResource(petDB));
    }

    @PostMapping
    public ResponseEntity<PetResource> createPet(@Valid @RequestBody SavePetResource savePetResource)  throws Exception {
        Pet pet =convertToEntity(savePetResource);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResource(petService.save(pet)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResource> updatePet(@PathVariable("id") Long id, @RequestBody SavePetResource resource) throws Exception {
        Pet pet = convertToEntity(resource);
        pet.setId(id);
        Pet petDB = petService.update(pet);
        if (petDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToResource(petDB));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PetResource> deletePet(@PathVariable("id") Long id) throws Exception{
        Pet petDB= petService.deleteById(id);
        if (petDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToResource(petDB));
    }

    private Pet convertToEntity(SavePetResource resource) {
        return mapper.map(resource, Pet.class);
    }

    private PetResource convertToResource(Pet entity) {
        return mapper.map(entity, PetResource.class);
    }
}