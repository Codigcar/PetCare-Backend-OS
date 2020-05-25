package com.pe.edu.upc.petcare.repository;


import com.pe.edu.upc.petcare.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
