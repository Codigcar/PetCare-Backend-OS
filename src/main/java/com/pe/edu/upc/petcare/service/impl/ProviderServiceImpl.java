package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.repository.ProviderRepository;
import com.pe.edu.upc.petcare.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    ProviderRepository providerRepository;

    @Override
    public Page<Provider> getAllProviders(Pageable pageable) {
        return providerRepository.findAll(pageable);
    }

    @Override
    public Provider getProviderById(Long providerId) {
        return providerRepository.findById(providerId)
                .orElseThrow(()->new ResourceNotFoundException("Provider","Id",providerId));
    }

    @Override
    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public Provider updateProvider(Long providerId, Provider providerRequest) {
        Provider provider=providerRepository.findById(providerId)
                .orElseThrow(()->new ResourceNotFoundException("Provider","Id",providerId));
        provider.setBusinessName(providerRequest.getBusinessName());
        provider.setRegion(providerRequest.getRegion());
        provider.setAddress(providerRequest.getAddress());
        provider.setField(providerRequest.getField());
        provider.setEmail(providerRequest.getEmail());
        provider.setDescription(provider.getDescription());

        return providerRepository.save(provider);
    }

    @Override
    public ResponseEntity<?> deleteProvider(Long providerId) {
        Provider provider=providerRepository.findById(providerId)
                .orElseThrow(()->new ResourceNotFoundException("Provider","Id",providerId));
        providerRepository.delete(provider);
        return ResponseEntity.ok().build();
    }
}
