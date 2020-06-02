package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeProductRepository extends JpaRepository<TypeProduct,Long> {
}
