package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.model.ProviderJoinTypeProduct;
import org.springframework.data.domain.Page;

public interface ProviderJoinProductService {
    ProviderJoinTypeProduct createRelationship (Long providerId, Long ProductId, ProviderJoinTypeProduct providerJoinTypeProduct);
}
