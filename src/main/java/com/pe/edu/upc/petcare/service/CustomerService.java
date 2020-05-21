package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Customer;
import org.apache.catalina.util.CustomObjectInputStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    Page<Customer> getAllCustomers(Pageable pageable);
    Customer getCustomerById(Long customerId);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long customerId, Customer customerRequest);
    ResponseEntity<?> deleteCustomer(Long customerId);
  //  Customer createCustomer(Customer customer);
    //List<Customer> findCustomerAll();
}
