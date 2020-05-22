package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Customer;
import com.pe.edu.upc.petcare.resource.CustomerResource;
import com.pe.edu.upc.petcare.resource.SaveCustomerResource;
import com.pe.edu.upc.petcare.service.CustomerService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CustomerService customerService;


    @GetMapping("/customers")
    public Page<CustomerResource> getAllCustomers(Pageable pageable){
        List<CustomerResource> customers = customerService.getAllCustomers(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int customerCount = customers.size();
        return new PageImpl<>(customers,pageable,customerCount);
    }

    @GetMapping("/customers/{id}")
    public CustomerResource getCustomerById(@PathVariable(name = "id")Long customerId){
        return convertToResource(customerService.getCustomerById(customerId));
    }

    @PostMapping("/customers")
    public CustomerResource createCustomer(@Valid @RequestBody SaveCustomerResource resource){
        return convertToResource(customerService.createCustomer(convertToEntity(resource)));
    }

    @PutMapping("/customers/{id}")
    public CustomerResource updateCustomer(@PathVariable(name = "id")Long customerId,
                                           @Valid @RequestBody SaveCustomerResource resource){
        return convertToResource(customerService.updateCustomer(customerId,convertToEntity(resource)));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") Long customerId){
        return customerService.deleteCustomer(customerId);
    }

    private Customer convertToEntity(SaveCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }

    private CustomerResource convertToResource(Customer entity) {
        return mapper.map(entity, CustomerResource.class);
    }

}
