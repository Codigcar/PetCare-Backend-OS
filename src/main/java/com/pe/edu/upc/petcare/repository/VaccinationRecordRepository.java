package com.pe.edu.upc.petcare.repository;
import com.pe.edu.upc.petcare.model.VaccinationRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord,Long> {
    Page<VaccinationRecord> findByMedicalProfileId(Long profileId, Pageable pageable);
    Optional<VaccinationRecord> findByIdAndMedicalProfileId(Long vaccinationRecordId, Long profileId);
}
