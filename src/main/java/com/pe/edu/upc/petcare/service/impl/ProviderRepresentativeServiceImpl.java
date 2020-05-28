package com.pe.edu.upc.petcare.service.impl;
import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.ProviderRepresentative;
import com.pe.edu.upc.petcare.repository.ProviderRepository;
import com.pe.edu.upc.petcare.repository.ProviderRepresentativeRepository;
import com.pe.edu.upc.petcare.service.ProviderRepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProviderRepresentativeServiceImpl implements ProviderRepresentativeService {

    @Autowired
    private ProviderRepresentativeRepository providerRepresentativeRepository;
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Page<ProviderRepresentative> getAllProviderRepresentativesByProviderId(Long providerId, Pageable pageable) {
        return providerRepresentativeRepository.findByProviderId(providerId,pageable);
    }

    @Override
    public ProviderRepresentative getProviderRepresentativeByIdAndProviderId(Long providerId, Long providerRepresentativeId) {
        return providerRepresentativeRepository.findByIdAndProviderId(providerRepresentativeId,providerId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Provider Representative not found with Id"+providerRepresentativeId+
                                "and ProviderId"+providerId));
    }

    @Override
    public ProviderRepresentative createProviderRepresentative(Long providerId, ProviderRepresentative providerRepresentative) {
        return providerRepository.findById(providerId).map(provider-> {
            providerRepresentative.setProvider(provider);
            return providerRepresentativeRepository.save(providerRepresentative);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Provider" + "Id" + providerId));
    }

    @Override
    public ProviderRepresentative updateProviderRepresentative(Long providerId, Long providerRepresentativeId,
                                                               ProviderRepresentative providerRepresentativeRequest) {
        if(!providerRepository.existsById(providerId))
            throw new ResourceNotFoundException("Provider","Id",providerId);

        return providerRepresentativeRepository.findById(providerRepresentativeId).map(providerRepresentative -> {
            providerRepresentative.setName(providerRepresentativeRequest.getName());
            providerRepresentative.setLastName(providerRepresentativeRequest.getLastName());
            providerRepresentative.setPosition(providerRepresentativeRequest.getPosition());
            providerRepresentative.setPhone1(providerRepresentativeRequest.getPhone1());
            providerRepresentative.setPhone2(providerRepresentativeRequest.getPhone2());

            return providerRepresentativeRepository.save(providerRepresentative);
        }).orElseThrow(()->new ResourceNotFoundException("ProviderRepresentative","Id",providerRepresentativeId));
    }

    @Override
    public ResponseEntity<?> deleteProviderRepresentative(Long providerId, Long providerRepresentativeId) {
        return providerRepresentativeRepository.findByIdAndProviderId(providerRepresentativeId,providerId)
                .map(providerRepresentative -> {
            providerRepresentativeRepository.delete(providerRepresentative);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Provider Representative not found with Id"+providerRepresentativeId+"and ProviderId"+providerId));
    }

}
