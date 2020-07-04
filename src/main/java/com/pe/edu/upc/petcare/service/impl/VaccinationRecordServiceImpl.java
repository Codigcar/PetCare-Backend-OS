package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.VaccinationRecord;
import com.pe.edu.upc.petcare.repository.MedicalProfileRepository;
import com.pe.edu.upc.petcare.repository.VaccinationRecordRepository;
import com.pe.edu.upc.petcare.service.VaccinationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VaccinationRecordServiceImpl implements VaccinationRecordService {
    @Autowired
    private MedicalProfileRepository medicalProfileRepository;
    @Autowired
    private VaccinationRecordRepository vaccinationRecordRepository;

    @Override
    public Page<VaccinationRecord> getAllVaccinationRecordsByMedicalProfileId(Long profileId, Pageable pageable) {
        return vaccinationRecordRepository.findByMedicalProfileId(profileId,pageable);
    }

    @Override
    public VaccinationRecord getVaccinationRecordByIdAndMedicalProfileId(Long profileId,Long vaccinationRecordId) {

        return vaccinationRecordRepository.findByIdAndMedicalProfileId(vaccinationRecordId,profileId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Profile not found with Id"+vaccinationRecordId+
                                "and ProfileId"+profileId));
    }

    @Override
    public VaccinationRecord createVaccinationRecord(Long profileId,VaccinationRecord vaccinationRecord  ) {
        return medicalProfileRepository.findById(profileId).map(profile -> {
            vaccinationRecord.setMedicalProfile(profile);

            return vaccinationRecordRepository.save(vaccinationRecord);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Profile" + "Id" + profileId));
    }

    @Override
    public VaccinationRecord updateVaccinationRecord(Long profileId,Long vaccinationRecordId,VaccinationRecord vaccinationRecordRequest) {
        if(!medicalProfileRepository.existsById(profileId))
            throw new ResourceNotFoundException("Profile","Id",profileId);

        return vaccinationRecordRepository.findById(vaccinationRecordId).map(vaccinationRecord -> {

            vaccinationRecord.setName(vaccinationRecordRequest.getName());
            vaccinationRecord.setDescription(vaccinationRecord.getDescription());


            return vaccinationRecordRepository.save(vaccinationRecord);
        }).orElseThrow(()->new ResourceNotFoundException("Profile","Id",profileId));
    }

    @Override
    public ResponseEntity<?> deleteVaccinationRecord(Long profileId,Long vaccinationRecordId) {
        return vaccinationRecordRepository.findByIdAndMedicalProfileId(vaccinationRecordId,profileId).map(vaccinationRecord -> {
            vaccinationRecordRepository.delete(vaccinationRecord);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "VaccinationRecord not found with Id"+vaccinationRecordId+"and ProfileId"+profileId));
    }

}
