package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>
{
}
