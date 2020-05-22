package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Customer;
import com.pe.edu.upc.petcare.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PetService {

    Page<Pet> getAllPetsByCustomerId(Long customerId, Pageable pageable);
    Pet getPetByIdAndCustomerId(Long customerId,Long petId);
    Pet createPet(Long customerId,Pet pet);
    Pet updatePet(Long customerId,Long petId,Pet petRequest);
    ResponseEntity<?> deletePet(Long customerId,Long petId);
}
