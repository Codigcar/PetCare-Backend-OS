package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Rol;
import com.pe.edu.upc.petcare.repository.PersonProfileRepository;
import com.pe.edu.upc.petcare.repository.RolRepository;
import com.pe.edu.upc.petcare.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {
    @Autowired
    private RolRepository rolRepository;
    @Override
    public Rol createRol(Rol rol)
    {
        return rolRepository.save(rol);
    }

    @Override
    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

}
