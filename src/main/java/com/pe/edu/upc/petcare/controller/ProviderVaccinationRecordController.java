package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.VaccinationRecord;
import com.pe.edu.upc.petcare.resource.PetResource;
import com.pe.edu.upc.petcare.resource.save.SaveVaccinationRecordResource;
import com.pe.edu.upc.petcare.resource.VaccinationRecordResource;
import com.pe.edu.upc.petcare.service.PersonProfileService;
import com.pe.edu.upc.petcare.service.PetService;
import com.pe.edu.upc.petcare.service.MedicalProfileService;
import com.pe.edu.upc.petcare.service.VaccinationRecordService;
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
@RequestMapping("/api/business/{businessId}/providers/{providerId}/people/{personId}/pets/{petId}/petprofiles/{petprofileId}/vaccinations")

public class ProviderVaccinationRecordController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private VaccinationRecordService vaccinationRecordService;
    @Autowired
    private MedicalProfileService medicalProfileService;
    @Autowired
    private PetService petService;
    @Autowired
    private PersonProfileService personProfileService;


   @GetMapping
      public Page<VaccinationRecordResource> getAllVaccinationRecordsByMedicalProfileId(@PathVariable(name = "petprofileId")Long profileId,
                                                      Pageable pageable){
        Page<VaccinationRecord>vaccinationRecordPage=vaccinationRecordService.getAllVaccinationRecordsByMedicalProfileId(profileId,pageable);
        List<VaccinationRecordResource> resources=vaccinationRecordPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
  }
    @GetMapping("/{vaccinationId}")
    public VaccinationRecordResource getVaccinationRecordByIdAndMedicalProfileId(@PathVariable(name = "vaccinationId")Long vaccinationId,@PathVariable(name = "petprofileId")Long profileId,@PathVariable(name = "petId")Long petId,@PathVariable(name = "personId")Long personId,
                                               @Valid @RequestBody SaveVaccinationRecordResource resource){
        personProfileService.getPersonById(personId);
        petService.getPetByPeopleId(personId,petId);
        medicalProfileService.getProfileByIdAndPetId(petId,profileId);

        return convertToResource(vaccinationRecordService.getVaccinationRecordByIdAndMedicalProfileId(profileId,vaccinationId));
    }



    @PostMapping
    public VaccinationRecordResource createVaccinationRecord(@PathVariable(name = "petprofileId")Long profileId,@PathVariable(name = "petId")Long petId,@PathVariable(name = "personId")Long customerId,
                                 @Valid @RequestBody SaveVaccinationRecordResource resource){


        personProfileService.getPersonById(customerId);
        petService.getPetByPeopleId(customerId,petId);
        medicalProfileService.getProfileByIdAndPetId(petId,profileId);
        return convertToResource(vaccinationRecordService.createVaccinationRecord(profileId,convertToEntity(resource)));
    }

    @PutMapping("/{vaccinationId}")
    public VaccinationRecordResource updateVaccinationRecord(@PathVariable(name = "petprofileId")Long profileId,@PathVariable(name = "petId")Long petId,@PathVariable(name = "personId")Long customerId,@PathVariable(name = "vaccinationId")Long vaccinationId,
                                                             @Valid @RequestBody SaveVaccinationRecordResource resource){

        personProfileService.getPersonById(customerId);
        petService.getPetByPeopleId(customerId,petId);
        medicalProfileService.getProfileByIdAndPetId(petId,profileId);
        return convertToResource(vaccinationRecordService.updateVaccinationRecord(profileId,vaccinationId,convertToEntity(resource)));
    }


    @DeleteMapping("/{vaccinationId}")
    public ResponseEntity<?> deleteProfile(@PathVariable(name = "petId")Long petId,@PathVariable(name = "personId")Long customerId,
                                       @PathVariable(name = "petprofileId")Long profileId,@PathVariable(name = "vaccinationId")Long vaccinationId){

        personProfileService.getPersonById(customerId);
        petService.getPetByPeopleId(customerId,petId);
        medicalProfileService.getProfileByIdAndPetId(petId,profileId);
        return vaccinationRecordService.deleteVaccinationRecord(profileId,vaccinationId);

    }

    private VaccinationRecord convertToEntity(SaveVaccinationRecordResource resource) {
        return mapper.map(resource, VaccinationRecord.class);
    }

    private VaccinationRecordResource convertToResource(VaccinationRecord entity) {
        return mapper.map(entity, VaccinationRecordResource.class);
    }
}
