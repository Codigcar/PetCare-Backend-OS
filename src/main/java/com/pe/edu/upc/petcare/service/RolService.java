package com.pe.edu.upc.petcare.service;



import com.pe.edu.upc.petcare.model.Rol;

import java.util.List;

public interface RolService {

    Rol createRol (Rol rol);
    List<Rol> getAllRoles();
}
