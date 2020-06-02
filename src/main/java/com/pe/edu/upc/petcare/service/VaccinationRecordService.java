package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.VaccinationRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface VaccinationRecordService {

    Page<VaccinationRecord> getAllVaccinationRecordsByMedicalProfileId(Long profileId, Pageable pageable);
    VaccinationRecord getVaccinationRecordByIdAndMedicalProfileId(Long profileId,Long vaccinationRecordId);
    VaccinationRecord createVaccinationRecord(Long profileId,VaccinationRecord vaccinationRecord  );
    VaccinationRecord updateVaccinationRecord(Long profileId,Long vaccinationRecordId,VaccinationRecord vaccinationRecord);
    ResponseEntity<?> deleteVaccinationRecord(Long profileId,Long vaccinationRecordId);
}
