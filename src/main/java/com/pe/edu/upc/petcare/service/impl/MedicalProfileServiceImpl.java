

package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.MedicalProfile;
import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.repository.PetRepository;
import com.pe.edu.upc.petcare.repository.MedicalProfileRepository;
import com.pe.edu.upc.petcare.service.MedicalProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MedicalProfileServiceImpl implements MedicalProfileService {
    @Autowired
    private MedicalProfileRepository medicalProfileRepository;
    @Autowired
    private PetRepository petRepository;


    @Override
    public Page<MedicalProfile> getAllProfilesByPetId(Long petId, Pageable pageable) {
        return medicalProfileRepository.findByPetId(petId,pageable);
    }

    @Override
    public MedicalProfile getProfileByIdAndPetId(Long petId, Long profileId) {

        return medicalProfileRepository.findByIdAndPetId(profileId,petId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Profile not found with Id"+profileId+
                                "and PetId"+petId));
    }

    @Override
    public MedicalProfile createProfile(Long petId, Provider provider, MedicalProfile medicalProfile) {
        return petRepository.findById(petId).map(pet -> {
            medicalProfile.setPet(pet);
            medicalProfile.setProvider(provider);
            return medicalProfileRepository.save(medicalProfile);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Pet" + "Id" + petId));
    }

    @Override
    public MedicalProfile updateProfile(Long petId, Provider provider,Long profileId, MedicalProfile medicalProfileRequest) {
        if(!petRepository.existsById(petId))
            throw new ResourceNotFoundException("Pet","Id",petId);

        return medicalProfileRepository.findById(profileId).map(profile -> {
            profile.setName(medicalProfileRequest.getName());
            profile.setWeight(medicalProfileRequest.getWeight());
            profile.setHeight(medicalProfileRequest.getHeight());
            profile.setLength(medicalProfileRequest.getLength());
            profile.setEyes(medicalProfileRequest.getEyes());
            profile.setBreed(medicalProfileRequest.getBreed());
            profile.setGender(medicalProfileRequest.getGender());
            profile.setColor(medicalProfileRequest.getColor());
            profile.setDescription(medicalProfileRequest.getDescription());
            profile.setPhoto(medicalProfileRequest.getPhoto());
            profile.setAge(medicalProfileRequest.getAge());
            profile.setProvider(provider);
            return medicalProfileRepository.save(profile);
        }).orElseThrow(()->new ResourceNotFoundException("Profile","Id",profileId));
    }

    @Override
    public ResponseEntity<?> deleteProfile(Long petId, Long profileId) {
        return medicalProfileRepository.findByIdAndPetId(profileId,petId).map(profile -> {
            medicalProfileRepository.delete(profile);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Profile not found with Id"+profileId+"and PetId"+petId));
    }

}



