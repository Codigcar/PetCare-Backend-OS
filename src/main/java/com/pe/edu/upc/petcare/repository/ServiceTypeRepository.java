package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType,Long> {
}
