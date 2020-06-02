package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.MedicalProfile;
import com.pe.edu.upc.petcare.resource.MedicalProfileResource;
import com.pe.edu.upc.petcare.resource.save.SaveMedicalProfileResource;
//import com.pe.edu.upc.petcare.resource.save.SaveVaccinationRecordResource;
import com.pe.edu.upc.petcare.service.PersonProfileService;
import com.pe.edu.upc.petcare.service.PetService;
import com.pe.edu.upc.petcare.service.MedicalProfileService;
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
@RequestMapping("/api/business/{businessId}/providers/{providerId}/people/{personId}/pets/{petId}/petprofiles")
public class MedicalProfileController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MedicalProfileService medicalProfileService;
    @Autowired
    private PetService petService;
    @Autowired
    private PersonProfileService personProfileService;


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
    public MedicalProfileResource createProfile(@PathVariable(name = "petId")Long petId, @PathVariable(name = "personId")Long customerId,
                                                @Valid @RequestBody SaveMedicalProfileResource resource){

        personProfileService.getPersonById(customerId);
        petService.getPetByIdAndPersonProfileId(customerId,petId);
        return convertToResource(medicalProfileService.createProfile(petId,convertToEntity(resource)));
    }

    @PutMapping("{profileId}")
    public MedicalProfileResource updateProfile(@PathVariable(name = "petId")Long petId, @PathVariable(name = "personId")Long customerId,
                                                @PathVariable(name = "profileId")Long profileId,
                                                @Valid @RequestBody SaveMedicalProfileResource resource){

        personProfileService.getPersonById(customerId);
        petService.getPetByIdAndPersonProfileId(customerId,petId);
        return convertToResource(medicalProfileService.updateProfile(petId,profileId,convertToEntity(resource)));
    }


    @DeleteMapping("{profileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable(name = "petId")Long petId,@PathVariable(name = "personId")Long customerId,
                                       @PathVariable(name = "profileId")Long profileId){

        personProfileService.getPersonById(customerId);
        petService.getPetByIdAndPersonProfileId(customerId,petId);
        return medicalProfileService.deleteProfile(petId,profileId);
    }

    private MedicalProfile convertToEntity(SaveMedicalProfileResource resource) {
        return mapper.map(resource, MedicalProfile.class);
    }

    private MedicalProfileResource convertToResource(MedicalProfile entity) {
        return mapper.map(entity, MedicalProfileResource.class);
    }
}
