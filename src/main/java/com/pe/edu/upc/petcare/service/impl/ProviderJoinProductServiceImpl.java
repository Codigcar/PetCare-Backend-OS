package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.model.ProviderJoinProductType;
import com.pe.edu.upc.petcare.repository.ProviderJoinProductTypeRepository;
import com.pe.edu.upc.petcare.repository.ProviderRepository;
import com.pe.edu.upc.petcare.repository.ProductTypeRepository;
import com.pe.edu.upc.petcare.service.ProviderJoinProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderJoinProductServiceImpl implements ProviderJoinProductService {
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private ProviderJoinProductTypeRepository providerJoinProductTypeRepository;

    @Override
    public ProviderJoinProductType createRelationship(Long providerId, Long productId, ProviderJoinProductType providerJoinProductType) {
        providerJoinProductType.setProvider(providerRepository.findById(providerId).orElse(null));
        providerJoinProductType.setProductType(productTypeRepository.findById(productId).orElse(null));
        return providerJoinProductTypeRepository.save(providerJoinProductType);
    }


}
