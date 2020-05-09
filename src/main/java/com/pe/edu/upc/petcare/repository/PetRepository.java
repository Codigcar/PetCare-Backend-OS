package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {
    //Pet findByNumberId(Long id);
}