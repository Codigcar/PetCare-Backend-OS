package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.Payment;
import com.pe.edu.upc.petcare.repository.PaymentRepository;
import com.pe.edu.upc.petcare.repository.ProviderRepository;
import com.pe.edu.upc.petcare.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Page<Payment> getAllPaymentsByProviderId(Long providerId, Pageable pageable){
        return paymentRepository.findByProviderId(providerId,pageable);
    }
    @Override
    public Payment getByIdAndProviderId(Long providerId, Long paymentId) {
        return paymentRepository.findByIdAndProviderId(providerId,paymentId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Payment not found with Id"+paymentId+
                                "and ProviderId"+providerId));
    }

    @Override
    public Payment createPayment(Long providerId, Payment payment) {
        return providerRepository.findById(providerId).map(provider -> {
            payment.setProvider(provider);
            return paymentRepository.save(payment);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Provider","Id",providerId));
    }

    @Override
    public Payment updatePayment(Long providerId, Long paymentId, Payment paymentRequest) {
        if(!providerRepository.existsById(providerId))
            throw new ResourceNotFoundException("Provider","Id",providerId);

        return paymentRepository.findById(paymentId).map(payment -> {
            payment.setCardNumber(paymentRequest.getCardNumber());
            payment.setCardName(paymentRequest.getCardName());
            payment.setCvvNumber(paymentRequest.getCvvNumber());
            payment.setExpiryDate(paymentRequest.getExpiryDate());

            return paymentRepository.save(payment);
        }).orElseThrow(()->new ResourceNotFoundException("Payment","Id",paymentId));
    }

    @Override
    public ResponseEntity<?> deletePayment(Long providerId, Long paymentId) {
        return paymentRepository.findByIdAndProviderId(providerId,paymentId).map(payment -> {
            paymentRepository.delete(payment);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Payment not found with Id"+paymentId+"and ProviderId"+providerId));
    }


}
