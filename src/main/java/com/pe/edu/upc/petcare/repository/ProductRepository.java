package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAllByProviderJoinProductTypeId(Long providerProductId, Pageable pageable);
    Optional<Product> findByIdAndProviderJoinProductTypeId(Long providerId,Long providerProductId);
}
