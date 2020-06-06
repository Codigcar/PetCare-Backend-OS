package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,Long> {
}
