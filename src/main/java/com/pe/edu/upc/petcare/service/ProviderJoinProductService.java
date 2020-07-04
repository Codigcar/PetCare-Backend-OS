package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.ProviderJoinProductType;

public interface ProviderJoinProductService {
    ProviderJoinProductType createRelationship (Long providerId, Long ProductId, ProviderJoinProductType providerJoinProductType);
}
