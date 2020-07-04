package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Rol;
import com.pe.edu.upc.petcare.model.VaccinationRecord;
import com.pe.edu.upc.petcare.resource.PersonProfileResource;
import com.pe.edu.upc.petcare.resource.RolResource;
import com.pe.edu.upc.petcare.resource.VaccinationRecordResource;
import com.pe.edu.upc.petcare.resource.save.SavePersonProfileResource;
import com.pe.edu.upc.petcare.resource.save.SaveRolResource;
import com.pe.edu.upc.petcare.service.PersonProfileService;
import com.pe.edu.upc.petcare.service.RolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/roles")
public class RolController {


    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RolService rolService;

    @PostMapping
    public RolResource createRol(@Valid @RequestBody SaveRolResource resource){
        return convertToResource(rolService.createRol(convertToEntity(resource)));
    }

    @GetMapping
    public ResponseEntity<List<RolResource>> getAllRoles(){
        List<Rol> rols = rolService.getAllRoles();
        List<RolResource> rolResources = rols.stream().map(this::convertToResource).collect(Collectors.toList());
        return ResponseEntity.ok(rolResources);
    }

    private RolResource convertToResource(Rol entity) {
        return mapper.map(entity, RolResource.class);
    }

    private Rol convertToEntity(SaveRolResource resource) {
        return mapper.map(resource, Rol.class);
    }
}
