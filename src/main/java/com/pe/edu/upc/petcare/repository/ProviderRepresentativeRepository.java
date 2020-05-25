package com.pe.edu.upc.petcare.repository;
import com.pe.edu.upc.petcare.model.ProviderRepresentative;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepresentativeRepository extends JpaRepository<ProviderRepresentative,Long>  {

    Page<ProviderRepresentative> findByProviderId(Long providerId, Pageable pageable);
    Optional<ProviderRepresentative> findByIdAndProviderId(Long providerrepresentativeId, Long providerId);
}
