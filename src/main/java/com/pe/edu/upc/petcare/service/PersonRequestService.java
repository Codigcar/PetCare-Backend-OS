package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.BusinessProfile;
import com.pe.edu.upc.petcare.model.PersonRequest;

import java.util.List;

public interface PersonRequestService {
    PersonRequest create (Long peopleId,Long petId,Long providerId,Long servicesId,PersonRequest personRequest);
    List<PersonRequest> getAllByProductId(Long productId);
}
