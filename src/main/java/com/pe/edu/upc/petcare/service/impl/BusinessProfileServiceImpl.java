package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.model.BusinessProfile;
import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.repository.BusinessProfileRepository;
import com.pe.edu.upc.petcare.repository.ProviderRepository;
import com.pe.edu.upc.petcare.service.BusinessProfileService;
import com.pe.edu.upc.petcare.service.PersonProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusinessProfileServiceImpl implements BusinessProfileService {

    @Autowired
    private BusinessProfileRepository businessProfileRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public BusinessProfile create(BusinessProfile businessProfile) {

        if (businessProfile.isOwner()){
            Provider provider = new Provider();
            provider.setBusinessName("name");
            provider.setAddress("address");
            provider.setDescription("description");
            provider.setEmail("email@email");
            provider.setField("field");
            provider.setRegion("region                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             ");

            providerRepository.save(provider);

            businessProfile.setProvider(provider);
        }
        else{
            Provider provider = providerRepository.findById(businessProfile.getProvider().getId()).orElse(null);
            businessProfile.setProvider(provider);
        }

        return businessProfileRepository.save(businessProfile);
    }
}
