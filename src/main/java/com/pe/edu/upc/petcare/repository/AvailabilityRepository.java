package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.Availability;
import com.pe.edu.upc.petcare.model.MedicalProfile;
import com.pe.edu.upc.petcare.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability,Long> {
    Page<Availability> findByProductId(Long personId, Pageable pageable);
    Optional<Availability> findByIdAndProductId(Long availabilityId, Long productId);
}
