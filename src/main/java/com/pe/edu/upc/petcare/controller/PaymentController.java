package com.pe.edu.upc.petcare.controller;


import com.pe.edu.upc.petcare.model.Payment;
import com.pe.edu.upc.petcare.resource.PaymentResource;
import com.pe.edu.upc.petcare.resource.SavePaymentResource;
import com.pe.edu.upc.petcare.service.PaymentService;
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
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PaymentService paymentService;


    @GetMapping
    public ResponseEntity<List<PaymentResource>> getAllCustomers() throws Exception  {
        List<Payment> payments = new ArrayList<>();

        payments = paymentService.findAll();
        if (payments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<PaymentResource> resources=payments.stream().map(this::convertToResource).collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResource> getPaymentById(@PathVariable("id") Long id) throws Exception{
        Payment paymentDB = paymentService.findById(id);
        if (paymentDB == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(convertToResource(paymentDB));
    }

    @PostMapping
    public ResponseEntity<PaymentResource> createPayment(@Valid @RequestBody SavePaymentResource savePaymentResource)  throws Exception {
        Payment payment=convertToEntity(savePaymentResource);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResource(paymentService.save(payment)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResource> updatePayment(@PathVariable("id") Long id, @RequestBody SavePaymentResource resource) throws Exception {
        Payment payment = convertToEntity(resource);
        payment.setId(id);
        Payment paymentDB = paymentService.update(payment);
        if (paymentDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToResource(paymentDB));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentResource> deletePayment(@PathVariable("id") Long id) throws Exception{
        Payment paymentDB= paymentService.deleteById(id);
        if (paymentDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToResource(paymentDB));
    }

    private Payment convertToEntity(SavePaymentResource resource) {
        return mapper.map(resource, Payment.class);
    }

    private PaymentResource convertToResource(Payment entity) {
        return mapper.map(entity, PaymentResource.class);
    }

}
