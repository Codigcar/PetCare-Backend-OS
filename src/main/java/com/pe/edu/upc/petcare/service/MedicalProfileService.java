package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.MedicalProfile;
import com.pe.edu.upc.petcare.model.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MedicalProfileService {

    Page<MedicalProfile> getAllProfilesByPetId(Long petId, Pageable pageable);
    MedicalProfile getProfileByIdAndPetId(Long petId, Long profileId);
    MedicalProfile createProfile(Long petId, Provider provider, MedicalProfile medicalProfile);
    MedicalProfile updateProfile(Long petId,Provider provider, Long profileId, MedicalProfile medicalProfileRequest);
    ResponseEntity<?> deleteProfile(Long petId,Long profileId);
}


