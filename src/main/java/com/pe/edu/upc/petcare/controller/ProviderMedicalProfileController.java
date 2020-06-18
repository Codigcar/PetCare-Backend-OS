

package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.MedicalProfile;
import com.pe.edu.upc.petcare.resource.MedicalProfileResource;
import com.pe.edu.upc.petcare.resource.save.SaveMedicalProfileResource;
//import com.pe.edu.upc.petcare.resource.save.SaveVaccinationRecordResource;
import com.pe.edu.upc.petcare.service.PersonProfileService;
import com.pe.edu.upc.petcare.service.PetService;
import com.pe.edu.upc.petcare.service.MedicalProfileService;
import com.pe.edu.upc.petcare.service.ProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/business/{businessId}/providers/{providerId}/people/{personId}/pets/{petId}/pet-profiles")
public class ProviderMedicalProfileController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MedicalProfileService medicalProfileService;
    @Autowired
    private PetService petService;
    @Autowired
    private PersonProfileService personProfileService;

    @Autowired
    private ProviderService providerProfileService;


    //   @GetMapping("customers/{customerId}/pets/{petId}/profiles")
    //   public Page<ProfileResource> getAllProfilesByPetId(@PathVariable(name = "petId")Long petId,
    //                                                   Pageable pageable){
//        Page<Profile> profilePage=profileService.getAllProfilesByPetId(petId,pageable);
    //      List<ProfileResource> resources=profilePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
    //       return new PageImpl<>(resources,pageable,resources.size());
    //   }


    @GetMapping
    public Page<MedicalProfileResource> getAllProfilesByPetId(@PathVariable(name = "petId")Long petId,
                                                              Pageable pageable){
        Page<MedicalProfile> profilePage= medicalProfileService.getAllProfilesByPetId(petId,pageable);
        List<MedicalProfileResource> resources=profilePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }






    @PostMapping
    public MedicalProfileResource createProfile(@PathVariable(name = "providerId")Long providerId,@PathVariable(name = "petId")Long petId, @PathVariable(name = "personId")Long customerId,
                                                @Valid @RequestBody SaveMedicalProfileResource resource){

        personProfileService.getPersonById(customerId);
        petService.getPetByPeopleId(customerId,petId);
        return convertToResource(medicalProfileService.createProfile(petId, providerProfileService.getProviderById(providerId),convertToEntity(resource)));
    }

    @PutMapping("/{petprofileId}")
    public MedicalProfileResource updateProfile(@PathVariable(name = "providerId")Long providerId,@PathVariable(name = "petId")Long petId, @PathVariable(name = "personId")Long customerId,
                                                @PathVariable(name = "petprofileId")Long profileId,
                                                @Valid @RequestBody SaveMedicalProfileResource resource){

        personProfileService.getPersonById(customerId);
        petService.getPetByPeopleId(customerId,petId);
        return convertToResource(medicalProfileService.updateProfile(petId,providerProfileService.getProviderById(providerId),profileId,convertToEntity(resource)));
    }


    @DeleteMapping("/{petprofileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable(name = "petId")Long petId,@PathVariable(name = "personId")Long customerId,
                                           @PathVariable(name = "petprofileId")Long profileId){

        personProfileService.getPersonById(customerId);
        petService.getPetByPeopleId(customerId,petId);
        return medicalProfileService.deleteProfile(petId,profileId);
    }

    private MedicalProfile convertToEntity(SaveMedicalProfileResource resource) {
        return mapper.map(resource, MedicalProfile.class);
    }

    private MedicalProfileResource convertToResource(MedicalProfile entity) {
        return mapper.map(entity, MedicalProfileResource.class);
    }
}



