package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.MedicalRecord;
import com.pe.edu.upc.petcare.repository.MedicalProfileRepository;
import com.pe.edu.upc.petcare.repository.MedicalRecordRepository;
import com.pe.edu.upc.petcare.repository.VaccinationRecordRepository;
import com.pe.edu.upc.petcare.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalProfileRepository medicalProfileRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;


    @Override
    public Page<MedicalRecord> getAllMedicalRecordsByMedicalProfileId(Long profileId, Pageable pageable) {
        return medicalRecordRepository.findByMedicalProfileId(profileId,pageable);
    }

    @Override
    public MedicalRecord getMedicalRecordByIdAndMedicalProfileId(Long profileId, Long medicalRecordId) {
        return medicalRecordRepository.findByIdAndMedicalProfileId(medicalRecordId,profileId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Profile not found with Id"+medicalRecordId+
                                "and ProfileId"+profileId));
    }

    @Override
    public MedicalRecord createMedicalRecord(Long profileId, MedicalRecord medicalRecord) {
        return medicalProfileRepository.findById(profileId).map(profile -> {
            medicalRecord.setMedicalProfile(profile);

            return medicalRecordRepository.save(medicalRecord);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Profile" + "Id" + profileId));
    }

    /*@Override
    public MedicalRecord updateMedicalRecord(Long profileId, Long medicalRecordId, MedicalRecord medicalRecordRequest) {
        if(!medicalProfileRepository.existsById(profileId))
            throw new ResourceNotFoundException("Profile","Id",profileId);

        return medicalRecordRepository.findById(medicalRecordId).map(medicalRecord -> {

            medicalRecord.setDescription(medicalRecordRequest.getDescription());
            medicalRecord.setAction(medicalRecord.getAction());
            medicalRecord.setType(medicalRecord.getType());

            return medicalRecordRepository.save(medicalRecord);
        }).orElseThrow(()->new ResourceNotFoundException("Profile","Id",profileId));
    }

    @Override
    public ResponseEntity<?> deleteMedicalRecord(Long profileId, Long medicalRecordId) {
        return medicalRecordRepository.findByIdAndMedicalProfileId(medicalRecordId,profileId).map(medicalRecord -> {
            medicalRecordRepository.delete(medicalRecord);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "MedicalRecord not found with Id"+medicalRecordId+"and ProfileId"+profileId));
    }

     */
}
