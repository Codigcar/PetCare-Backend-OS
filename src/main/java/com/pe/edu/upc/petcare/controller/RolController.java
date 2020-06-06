package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Rol;
import com.pe.edu.upc.petcare.resource.PersonProfileResource;
import com.pe.edu.upc.petcare.resource.RolResource;
import com.pe.edu.upc.petcare.resource.save.SavePersonProfileResource;
import com.pe.edu.upc.petcare.resource.save.SaveRolResource;
import com.pe.edu.upc.petcare.service.PersonProfileService;
import com.pe.edu.upc.petcare.service.RolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    private RolResource convertToResource(Rol entity) {
        return mapper.map(entity, RolResource.class);
    }

    private Rol convertToEntity(SaveRolResource resource) {
        return mapper.map(resource, Rol.class);
    }
}
