package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider,Long> {
}
