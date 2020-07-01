package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.repository.PersonProfileRepository;
import com.pe.edu.upc.petcare.repository.PetRepository;
import com.pe.edu.upc.petcare.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PersonProfileRepository personProfileRepository;

    @Override
    public List<Pet> getAllPetsByPersonProfileId(Long personId, Pageable pageable) {
        return petRepository.findByPersonProfileId(personId,pageable);
    }

    @Override
    public Pet getPetByPeopleId(Long personId, Long petId) {
        return petRepository.findByIdAndPersonProfileId(petId,personId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Pet not found with Id"+petId+
                                "and CustomerId"+personId));
    }

    @Override
    public Pet createPet(Long personId, Pet pet) {
        return personProfileRepository.findById(personId).map(customer -> {
            pet.setPersonProfile(customer);
            return petRepository.save(pet);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Customer" + "Id" + personId));
    }

    @Override
    public Pet updatePet(Long personId, Long petId, Pet petRequest) {
        if(!personProfileRepository.existsById(personId))
            throw new ResourceNotFoundException("Customer","Id",personId);

        return petRepository.findById(petId).map(pet -> {
            pet.setName(petRequest.getName());
            pet.setAge(petRequest.getAge());
            pet.setBreed(petRequest.getBreed());
            pet.setPhoto(petRequest.getPhoto());
            pet.setGender(petRequest.getGender());

            return petRepository.save(pet);
        }).orElseThrow(()->new ResourceNotFoundException("Pet","Id",petId));
    }

    @Override
    public ResponseEntity<?> deletePet(Long personId, Long petId) {
        return petRepository.findByIdAndPersonProfileId(petId,personId).map(pet -> {
            petRepository.delete(pet);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Pet not found with Id"+petId+"and CustomerId"+personId));
    }
}