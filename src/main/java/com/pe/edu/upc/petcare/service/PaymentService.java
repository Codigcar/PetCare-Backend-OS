package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface PaymentService {
    Page<Payment> getAllPaymentsByProviderId(Long providerId,Pageable pageable);
    Payment getByIdAndProviderId(Long providerId, Long paymentId);
    Payment createPayment(Long providerId,Payment payment);
    Payment updatePayment(Long providerId,Long paymentId, Payment paymentRequest);
    ResponseEntity<?> deletePayment(Long providerId, Long paymentId);

}
