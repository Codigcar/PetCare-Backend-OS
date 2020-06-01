package com.pe.edu.upc.petcare.service.impl;


import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.repository.PersonProfileRepository;
import com.pe.edu.upc.petcare.service.PersonProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonProfileServiceImpl implements PersonProfileService {

    @Autowired
    private PersonProfileRepository personProfileRepository;

    @Override
    public Page<PersonProfile> getAllCustomers(Pageable pageable) {
        return personProfileRepository.findAll(pageable);
    }

    @Override
    public PersonProfile getCustomerById(Long customerId) {
        return personProfileRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","Id",customerId));
    }

    @Override
    public PersonProfile createCustomer(PersonProfile personProfile)
    {
        return personProfileRepository.save(personProfile);
    }

    @Override
    public PersonProfile updateCustomer(Long customerId, PersonProfile personProfileRequest) {
        PersonProfile personProfile = personProfileRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","Id",customerId));
        personProfile.setFirstName(personProfileRequest.getFirstName());
        personProfile.setLastName(personProfileRequest.getLastName());
        personProfile.setDocument(personProfileRequest.getDocument());
        personProfile.setEmail(personProfileRequest.getEmail());
        personProfile.setPhone(personProfileRequest.getPhone());
        personProfile.setAge(personProfileRequest.getAge());

        return personProfileRepository.save(personProfile);
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Long customerId) {
        PersonProfile personProfile = personProfileRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","Id",customerId));
        personProfileRepository.delete(personProfile);
        return ResponseEntity.ok().build();
    }
}
