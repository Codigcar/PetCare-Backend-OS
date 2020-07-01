package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PetService {

    List<Pet> getAllPetsByPersonProfileId(Long personId, Pageable pageable);
    Pet getPetByPeopleId(Long personId,Long petId);
    Pet createPet(Long personId,Pet pet);
    Pet updatePet(Long personId,Long petId,Pet petRequest);
    ResponseEntity<?> deletePet(Long personId,Long petId);
}
