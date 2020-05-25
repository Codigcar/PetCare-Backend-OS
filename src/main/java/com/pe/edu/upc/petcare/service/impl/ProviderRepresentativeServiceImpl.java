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
    private ProviderRepresentativeRepository providerrepresentativeRepository;
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Page<ProviderRepresentative> getAllProviderRepresentativesByProviderId(Long providerId, Pageable pageable) {
        return providerrepresentativeRepository.findByProviderId(providerId,pageable);
    }

    @Override
    public ProviderRepresentative getProviderRepresentativeByIdAndProviderId(Long providerId, Long providerrepresentativeId) {
        return providerrepresentativeRepository.findByIdAndProviderId(providerrepresentativeId,providerId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Provider Representative not found with Id"+providerrepresentativeId+
                                "and ProviderId"+providerId));
    }

    @Override
    public ProviderRepresentative createProviderRepresentative(Long providerId, ProviderRepresentative providerrepresentative) {
        return providerRepository.findById(providerId).map(provider-> {
            providerrepresentative.setProvider(provider);
            return providerrepresentativeRepository.save(providerrepresentative);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Provider" + "Id" + providerId));
    }

    @Override
    public ProviderRepresentative updateProviderRepresentative(Long providerId, Long providerrepresentativeId,
                                                               ProviderRepresentative providerrepresentativeRequest) {
        if(!providerRepository.existsById(providerId))
            throw new ResourceNotFoundException("Provider","Id",providerId);

        return providerrepresentativeRepository.findById(providerrepresentativeId).map(providerrepresentative -> {
            providerrepresentative.setName(providerrepresentativeRequest.getName());
            providerrepresentative.setLastName(providerrepresentativeRequest.getLastName());
            providerrepresentative.setPosition(providerrepresentativeRequest.getPosition());
            providerrepresentative.setPhone1(providerrepresentativeRequest.getPhone1());
            providerrepresentative.setPhone2(providerrepresentativeRequest.getPhone2());

            return providerrepresentativeRepository.save(providerrepresentative);
        }).orElseThrow(()->new ResourceNotFoundException("ProviderRepresentative","Id",providerrepresentativeId));
    }

    @Override
    public ResponseEntity<?> deleteProviderRepresentative(Long providerId, Long providerrepresentativeId) {
        return providerrepresentativeRepository.findByIdAndProviderId(providerrepresentativeId,providerId)
                .map(providerrepresentative -> {
            providerrepresentativeRepository.delete(providerrepresentative);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Provider Representative not found with Id"+providerrepresentativeId+"and ProviderId"+providerId));
    }

}
