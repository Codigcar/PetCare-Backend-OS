package com.pe.edu.upc.petcare.service.impl;


import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.Customer;
import com.pe.edu.upc.petcare.repository.CustomerRepository;
import com.pe.edu.upc.petcare.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","Id",customerId));
    }

    @Override
    public Customer createCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customerRequest) {
        Customer customer=customerRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","Id",customerId));
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setDocument(customerRequest.getDocument());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        customer.setAge(customerRequest.getAge());

        return customerRepository.save(customer);
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","Id",customerId));
        customerRepository.delete(customer);
        return ResponseEntity.ok().build();
    }
}
