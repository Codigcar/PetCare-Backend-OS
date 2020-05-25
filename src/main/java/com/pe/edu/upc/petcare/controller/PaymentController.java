package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Payment;
import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.resource.PaymentResource;
import com.pe.edu.upc.petcare.resource.PetResource;
import com.pe.edu.upc.petcare.resource.SavePaymentResource;
import com.pe.edu.upc.petcare.resource.SavePetResource;
import com.pe.edu.upc.petcare.service.PaymentService;
import com.pe.edu.upc.petcare.service.PetService;
import com.pe.edu.upc.petcare.service.ProviderService;
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
public class PaymentController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/providers/{providerId}/payments")
    Page<PaymentResource> getAllPaymentsByProviderId(@PathVariable(name = "providerId")Long providerId,
                                             Pageable pageable)
    {
        Page<Payment> paymentPage=paymentService.getAllPaymentsByProviderId(providerId,pageable);
        List<PaymentResource> resources=paymentPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @GetMapping("/provider/{providerId}/payments/{paymentId}")
    public PaymentResource getPaymentByIdProviderId(@PathVariable(name = "providerId")Long providerId,
                                                  @PathVariable(name = "paymentId")Long paymentId){
        return convertToResource(paymentService.getByIdAndProviderId(providerId,paymentId));
    }


    @PostMapping("/providers/{providerId}/payments")
    public PaymentResource createPayment(@PathVariable(name = "providerId")Long providerId,
                                         @Valid @RequestBody SavePaymentResource resource){
        return convertToResource(paymentService.createPayment(providerId,convertToEntity(resource)));
    }

    @PutMapping("/providers/{providerId}/payments/{paymentId}")
    public PaymentResource updatePayment(@PathVariable(name = "providerId")Long providerId,
                                         @PathVariable(name = "paymentId")Long paymentId,
                                         @Valid @RequestBody SavePaymentResource resource){
        return convertToResource(paymentService.updatePayment(providerId,paymentId,convertToEntity(resource)));
    }

    @DeleteMapping("/providers/{providerId}/payments/{paymentId}")
    public ResponseEntity<?> deletePayment(@PathVariable(name = "providerId")Long providerId,
                                           @PathVariable(name = "paymentId")Long paymentId){
        return paymentService.deletePayment(providerId,paymentId);
    }

    private Payment convertToEntity(SavePaymentResource resource) {
        return mapper.map(resource, Payment.class);
    }

    private PaymentResource convertToResource(Payment entity) {
        return mapper.map(entity, PaymentResource.class);
    }

}
