package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.MedicalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    Page<MedicalRecord> findByMedicalProfileId(Long profileId, Pageable pageable);
    Optional<MedicalRecord> findByIdAndMedicalProfileId(Long medicalRecordId, Long profileId);
}
