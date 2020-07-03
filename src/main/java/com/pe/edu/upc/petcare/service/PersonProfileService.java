package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.PersonProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonProfileService {
    List<PersonProfile> getAllCustomers();
    PersonProfile getPersonById(Long personId);
    PersonProfile createCustomer(PersonProfile personProfile);
    PersonProfile updateCustomer(Long customerId, PersonProfile personProfileRequest);
    ResponseEntity<?> deleteCustomer(Long customerId);
    //List<PersonProfile> getPersonProfileByPersonId (Long personId, Pageable pageable);

}
