package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.PersonProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PersonProfileService {
    Page<PersonProfile> getAllCustomers(Pageable pageable);
    PersonProfile getCustomerById(Long customerId);
    PersonProfile createCustomer(PersonProfile personProfile);
    PersonProfile updateCustomer(Long customerId, PersonProfile personProfileRequest);
    ResponseEntity<?> deleteCustomer(Long customerId);
  //  Customer createCustomer(Customer customer);
    //List<Customer> findCustomerAll();
}
