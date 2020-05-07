package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Customer;
import com.pe.edu.upc.petcare.resource.CustomerResource;
import com.pe.edu.upc.petcare.resource.SaveCustomerResource;
import com.pe.edu.upc.petcare.service.CustomerService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CustomerService customerService;


    @GetMapping
    public ResponseEntity<List<CustomerResource>> getAllCustomers() throws Exception  {
        List<Customer> customers = new ArrayList<>();

        customers = customerService.findAll();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
       List<CustomerResource> resources = customers.stream().map(this::convertToResource).collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) throws Exception{
        Customer customerDB = customerService.findById(id);
        if (customerDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerDB);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer product)  throws Exception {

        Customer productCreate = customerService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) throws Exception {
        customer.setId(id);
        Customer customerDB = customerService.update(customer);
        if (customerDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) throws Exception{
        Customer customerDB= customerService.deleteById(id);
        if (customerDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerDB);
    }

    private Customer convertToEntity(SaveCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }

    private CustomerResource convertToResource(Customer entity) {
        return mapper.map(entity, CustomerResource.class);
    }

}
