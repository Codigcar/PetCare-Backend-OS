package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.ProviderRepresentative;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProviderRepresentativeService {

    Page<ProviderRepresentative> getAllProviderRepresentativesByProviderId(Long providerId, Pageable pageable);
    ProviderRepresentative getProviderRepresentativeByIdAndProviderId(Long providerId,Long providerRepresentativeId);
    ProviderRepresentative createProviderRepresentative(Long providerId,ProviderRepresentative pet);
    ProviderRepresentative updateProviderRepresentative(Long providerId,Long petId,ProviderRepresentative providerRepresentativeRequest);
    ResponseEntity<?> deleteProviderRepresentative(Long providerId, Long providerRepresentativeId);
}
