package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.BusinessProfile;
import com.pe.edu.upc.petcare.model.PersonProfile;

import java.util.List;

public interface BusinessProfileService {
    BusinessProfile create (BusinessProfile businessProfile);
    List<BusinessProfile> getAllBusiness();
    BusinessProfile getBusinessById(Long businessId);
}
