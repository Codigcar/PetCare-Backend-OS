package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.ProviderJoinTypeProduct;
import com.pe.edu.upc.petcare.repository.ProviderJoinTypeProductRepository;
import com.pe.edu.upc.petcare.repository.ProviderRepository;
import com.pe.edu.upc.petcare.repository.TypeProductRepository;
import com.pe.edu.upc.petcare.service.ProviderJoinProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderJoinProductServiceImpl implements ProviderJoinProductService {
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private TypeProductRepository typeProductRepository;
    @Autowired
    private ProviderJoinTypeProductRepository providerJoinTypeProductRepository;

    @Override
    public ProviderJoinTypeProduct createRelationship(Long providerId, Long productId, ProviderJoinTypeProduct providerJoinTypeProduct) {
        providerJoinTypeProduct.setProvider(providerRepository.findById(providerId).orElse(null));
        providerJoinTypeProduct.setTypeProduct(typeProductRepository.findById(productId).orElse(null));
        return providerJoinTypeProductRepository.save(providerJoinTypeProduct);
    }


}
