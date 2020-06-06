package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Affiliation;
import com.pe.edu.upc.petcare.model.BusinessProfile;
import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Provider;

import java.util.List;

public interface AffiliationService {
    Affiliation create (Long personId,Long providerId, Affiliation affiliation);
    List<PersonProfile> getAllPersonByProviderId(Provider provider);
}
