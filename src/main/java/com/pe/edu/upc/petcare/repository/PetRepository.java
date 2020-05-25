package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {
    Page<Pet> findByCustomerId(Long customerId, Pageable pageable);
    Optional<Pet> findByIdAndCustomerId(Long petId, Long customerId);
}