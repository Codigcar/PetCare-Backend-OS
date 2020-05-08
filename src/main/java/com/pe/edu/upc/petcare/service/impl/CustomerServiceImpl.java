package com.pe.edu.upc.petcare.service.impl;


import com.pe.edu.upc.petcare.model.Customer;
import com.pe.edu.upc.petcare.repository.CustomerRepository;
import com.pe.edu.upc.petcare.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
/*
    @Override
    public List<Customer> findCustomerAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        /*Customer customerDB= customerRepository.findByNumberId((customer.getNumberId()));
        if (customerDB != null){
            return customerDB;
        }
        customer.setState("CREATED");
       // Customer customerDB = customerRepository.save(customer);
        return customerRepository.save(customer);
    }
*/
    @Override
    public Customer save(Customer customer) throws Exception {
        Customer customerDB= customerRepository.findById((customer.getId())).orElse(null);
        if (customerDB != null){
            return customerDB;
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) throws Exception {
        Customer customerDB= customerRepository.findById(customer.getId()).orElse(null);
        if (customerDB == null){
            return null;
        }
        customerDB.setFirstname(customer.getFirstname());
        customerDB.setLastname(customer.getLastname());
        customerDB.setDocument(customer.getDocument());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhone(customer.getPhone());
        customerDB.setAge(customer.getAge());

        return customerRepository.save(customerDB);
    }



    @Override
    public Customer findById(Long id) throws Exception {
        return customerRepository.findById(id).orElse(null);
    }



    @Override
    public List<Customer> findAll() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public Customer deleteById(Long id) throws Exception {
        Customer customerDB= customerRepository.findById(id).orElse(null);

        if (customerDB != null){
            customerRepository.deleteById(customerDB.getId());
            return customerDB;
        }

        return null;
    }
}
