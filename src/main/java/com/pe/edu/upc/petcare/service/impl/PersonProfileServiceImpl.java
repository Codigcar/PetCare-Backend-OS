package com.pe.edu.upc.petcare.service.impl;


import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.Account;
import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.repository.AccountRepository;
import com.pe.edu.upc.petcare.repository.PersonProfileRepository;
import com.pe.edu.upc.petcare.repository.RolRepository;
import com.pe.edu.upc.petcare.repository.SubscriptionPlanRepository;
import com.pe.edu.upc.petcare.service.PersonProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonProfileServiceImpl implements PersonProfileService {

    @Autowired
    private PersonProfileRepository personProfileRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;
    
    @Override
    public List<PersonProfile> getAllCustomers() {
        return personProfileRepository.findAll();
    }

    @Override
    public PersonProfile getPersonById(Long customerId) {
        return personProfileRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","Id",customerId));
    }

    @Override
    public PersonProfile createCustomer(PersonProfile personProfile)
    {
        Account account = new Account();
        account.setUser(personProfile.getEmail());
        account.setPassword(personProfile.getPassword());

        account.setRol(rolRepository.findById((long) 1).orElse(null));

        account.setSubscriptionPlan(subscriptionPlanRepository.findById((long) 1).orElse(null));

        accountRepository.save(account);

        personProfile.setAccount(account);
        return personProfileRepository.save(personProfile);
    }

    @Override
    public PersonProfile updateCustomer(Long customerId, PersonProfile personProfileRequest) {
        PersonProfile personProfile = personProfileRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","Id",customerId));
        personProfile.setName(personProfileRequest.getName());
        personProfile.setLastName(personProfileRequest.getLastName());
        personProfile.setDocument(personProfileRequest.getDocument());
        personProfile.setEmail(personProfileRequest.getEmail());
        personProfile.setPhone(personProfileRequest.getPhone());
        personProfile.setAge(personProfileRequest.getAge());
        personProfile.setPhoto(personProfileRequest.getPhoto());

        return personProfileRepository.save(personProfile);
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Long customerId) {
        PersonProfile personProfile = personProfileRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","Id",customerId));
        personProfileRepository.delete(personProfile);
        return ResponseEntity.ok().build();
    }

    /*@Override
    public List<PersonProfile> getPersonProfileByPersonId(Long personId, Pageable pageable) {
        return personProfileRepository.findByProviderId(personId,pageable);
    }*/
}
