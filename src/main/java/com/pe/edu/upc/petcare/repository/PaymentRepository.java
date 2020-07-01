package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Page<Payment> findByProviderId(Long providerId, Pageable pageable);
    Optional<Payment> findByIdAndProviderId(Long providerId,Long paymentId);
}
