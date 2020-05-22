package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.Customer;
import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.repository.CustomerRepository;
import com.pe.edu.upc.petcare.repository.PetRepository;
import com.pe.edu.upc.petcare.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Pet> getAllPetsByCustomerId(Long customerId, Pageable pageable) {
        return petRepository.findByCustomerId(customerId,pageable);
    }

    @Override
    public Pet getPetByIdAndCustomerId(Long customerId, Long petId) {
        return petRepository.findByIdAndCustomerId(petId,customerId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Pet not found with Id"+petId+
                                "and CustomerId"+customerId));
    }

    @Override
    public Pet createPet(Long customerId, Pet pet) {
        return customerRepository.findById(customerId).map(customer -> {
            pet.setCustomer(customer);
            return petRepository.save(pet);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Customer" + "Id" + customerId));
    }

    @Override
    public Pet updatePet(Long customerId, Long petId, Pet petRequest) {
        if(!customerRepository.existsById(customerId))
            throw new ResourceNotFoundException("Customer","Id",customerId);

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
    public ResponseEntity<?> deletePet(Long customerId, Long petId) {
        return petRepository.findByIdAndCustomerId(petId,customerId).map(pet -> {
            petRepository.delete(pet);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Pet not found with Id"+petId+"and CustomerId"+customerId));
    }
}