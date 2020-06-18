package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.MedicalRecord;
import com.pe.edu.upc.petcare.resource.MedicalRecordResource;
import com.pe.edu.upc.petcare.resource.save.SaveMedicalRecordResource;
import com.pe.edu.upc.petcare.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/business/{businessId}/providers/{providerId}/people/{personId}/pets/{petId}/petprofiles/{petprofileId}/medicalrecords")

public class ProviderMedicalRecordController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private MedicalRecordService medicalRecordService;
    @Autowired
    private MedicalProfileService medicalProfileService;
    @Autowired
    private PetService petService;
    @Autowired
    private PersonProfileService personProfileService;

    @GetMapping
    public Page<MedicalRecordResource> getAllMedicalRecordsByMedicalProfileId(@PathVariable(name = "petprofileId")Long profileId,
                                                                              Pageable pageable){
        Page<MedicalRecord>medicalRecordPage=medicalRecordService.getAllMedicalRecordsByMedicalProfileId(profileId,pageable);
        List<MedicalRecordResource> resources=medicalRecordPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @GetMapping("/{medicalRecordsId}")
    public MedicalRecordResource getMedicalRecordByIdAndMedicalProfileId(@PathVariable(name = "medicalRecordsId")Long medicalRecordId,@PathVariable(name = "petprofileId")Long profileId,@PathVariable(name = "petId")Long petId,@PathVariable(name = "personId")Long personId,
                                                                                 @Valid @RequestBody SaveMedicalRecordResource resource){
        personProfileService.getPersonById(personId);
        petService.getPetByPeopleId(personId,petId);
        medicalProfileService.getProfileByIdAndPetId(petId,profileId);

        return convertToResource(medicalRecordService.getMedicalRecordByIdAndMedicalProfileId(profileId,medicalRecordId));
    }

    @PostMapping
    public MedicalRecordResource createMedicalRecord(@PathVariable(name = "petprofileId")Long profileId,@PathVariable(name = "petId")Long petId,@PathVariable(name = "personId")Long customerId,
                                                             @Valid @RequestBody SaveMedicalRecordResource resource){


        personProfileService.getPersonById(customerId);
        petService.getPetByPeopleId(customerId,petId);
        medicalProfileService.getProfileByIdAndPetId(petId,profileId);

        return convertToResource(medicalRecordService.createMedicalRecord(profileId,convertToEntity(resource)));
    }

    private MedicalRecord convertToEntity(SaveMedicalRecordResource resource) {
        return mapper.map(resource, MedicalRecord.class);
    }

    private MedicalRecordResource convertToResource(MedicalRecord entity) {
        return mapper.map(entity, MedicalRecordResource.class);
    }

}
