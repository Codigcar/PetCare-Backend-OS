package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.model.Affiliation;
import com.pe.edu.upc.petcare.model.BusinessProfile;
import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.repository.AffiliationRepository;
import com.pe.edu.upc.petcare.repository.BusinessProfileRepository;
import com.pe.edu.upc.petcare.repository.PersonProfileRepository;
import com.pe.edu.upc.petcare.repository.ProviderRepository;
import com.pe.edu.upc.petcare.service.AffiliationService;
import com.pe.edu.upc.petcare.service.BusinessProfileService;
import com.pe.edu.upc.petcare.service.PersonProfileService;
import com.pe.edu.upc.petcare.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffiliationServiceImpl implements AffiliationService {

    @Autowired
    private AffiliationRepository affiliationRepository;

    @Autowired
    private PersonProfileRepository personProfileRepository;

    @Autowired
    private ProviderRepository providerRepository;


    @Override
    public Affiliation create(Long personId,Long providerId,Affiliation affiliationId) {
        Affiliation affiliation = new Affiliation();
        affiliation.setPerson(personProfileRepository.findById(personId).orElse(null));
        affiliation.setProvider(providerRepository.findById(providerId).orElse(null));
        return affiliationRepository.save(affiliation);
    }


    @Override
    public List<PersonProfile> getAllPersonByProviderId(Provider provider) {
        return affiliationRepository.findByProvider(provider);
    }
}
