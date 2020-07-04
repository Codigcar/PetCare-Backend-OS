package com.pe.edu.upc.petcare.repository;
import com.pe.edu.upc.petcare.model.MedicalProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalProfileRepository extends JpaRepository<MedicalProfile,Long> {
    Page<MedicalProfile> findByPetId(Long petId, Pageable pageable);
    Optional<MedicalProfile> findByIdAndPetId(Long profileId, Long petId);
}
