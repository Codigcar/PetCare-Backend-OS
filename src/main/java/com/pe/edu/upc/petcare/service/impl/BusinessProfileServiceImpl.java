package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.model.Account;
import com.pe.edu.upc.petcare.model.BusinessProfile;
import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.repository.*;
import com.pe.edu.upc.petcare.service.BusinessProfileService;
import com.pe.edu.upc.petcare.service.PersonProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessProfileServiceImpl implements BusinessProfileService {

    @Autowired
    private BusinessProfileRepository businessProfileRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;


    @Override
    public BusinessProfile create(BusinessProfile businessProfile) {

        Account account = new Account();

        if (businessProfile.isOwner()){
            Provider provider = new Provider();
            provider.setBusinessName("name");
            provider.setAddress("address");
            provider.setDescription("description");
            provider.setEmail("email@email");
            provider.setField("field");
            provider.setRegion("region");
            provider.setSubscriptionPlan(1);
            providerRepository.save(provider);

            businessProfile.setProvider(provider);
        }
        else{
            Provider provider = providerRepository.findById(businessProfile.getProvider().getId()).orElse(null);
            businessProfile.setProvider(provider);
        }


        account.setUser(businessProfile.getEmail());
        account.setPassword(businessProfile.getPassword());
        account.setSubscriptionPlan(subscriptionPlanRepository.findById((long) 1).orElse(null));
        account.setRol(rolRepository.findById((long) 2).orElse(null));

        accountRepository.save(account);

        businessProfile.setAccount(account);

        return businessProfileRepository.save(businessProfile);

    }

    @Override
    public List<BusinessProfile> getAllBusiness() {
        return businessProfileRepository.findAll();
    }

    @Override
    public BusinessProfile getBusinessById(Long businessId) {
        return businessProfileRepository.findById(businessId).orElse(null);
    }
}
