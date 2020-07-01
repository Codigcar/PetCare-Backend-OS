package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProviderService {
    List<Provider> getAllProviders(Pageable pageable);
    Provider getProviderById(Long providerId);
    Provider createProvider(Provider provider);
    Provider updateProvider(Long businessId,Long providerId,Provider providerRequest);
    ResponseEntity<?> deleteProvider(Long providerId);
}
