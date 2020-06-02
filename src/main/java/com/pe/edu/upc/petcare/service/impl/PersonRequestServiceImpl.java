package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.model.BusinessProfile;
import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.PersonRequest;
import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.repository.*;
import com.pe.edu.upc.petcare.service.BusinessProfileService;
import com.pe.edu.upc.petcare.service.PersonRequestService;
import com.pe.edu.upc.petcare.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonRequestServiceImpl implements PersonRequestService {

    @Autowired
    private PersonRequestRepository personRequestRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PersonProfileRepository personProfileRepository;


    @Override
    public PersonRequest create(Long peopleId, Long petId, Long providerId, Long servicesId, PersonRequest personRequest) {

        personRequest.setPersonProfile(personProfileRepository.findById(peopleId).orElse(null));
        personRequest.setProduct(productRepository.findById(servicesId).orElse(null));

        return personRequestRepository.save(personRequest);
    }

    @Override
    public List<PersonRequest> getAllByProductId(Long productId) {
        return personRequestRepository.getAllByProductId(productId);
    }
}
