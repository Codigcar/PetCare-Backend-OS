package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.MedicalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MedicalRecordService {

    Page<MedicalRecord> getAllMedicalRecordsByMedicalProfileId(Long profileId, Pageable pageable);
    MedicalRecord getMedicalRecordByIdAndMedicalProfileId(Long profileId, Long medicalRecordId);
    MedicalRecord createMedicalRecord(Long profileId, MedicalRecord medicalRecord);
    //MedicalRecord updateMedicalRecord(Long profileId, Long medicalRecordId, MedicalRecord medicalRecord);
    //ResponseEntity<?> deleteMedicalRecord(Long profileId, Long medicalRecordId);

}
