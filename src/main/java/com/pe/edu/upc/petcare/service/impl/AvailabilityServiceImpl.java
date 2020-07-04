package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.Availability;
import com.pe.edu.upc.petcare.repository.AvailabilityRepository;
import com.pe.edu.upc.petcare.repository.ProductRepository;
import com.pe.edu.upc.petcare.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Availability> getAllAvailabilitiesByProductId(Long productId, Pageable pageable) {
        return availabilityRepository.findByProductId(productId,pageable);
    }

    @Override
    public Availability createAvailability(Long productId, Availability availability) {
        return productRepository.findById(productId).map(product -> {
            availability.setProduct(product);
            return availabilityRepository.save(availability);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Product" + "Id" + productId));
    }

    @Override
    public Availability updateAvailability(Long productId, Long availabilityId, Availability availabilityDetails) {
        if(!productRepository.existsById(productId))
            throw new ResourceNotFoundException("Customer","Id",productId);

        return availabilityRepository.findById(availabilityId).map(availability -> {
            availability.setDateAvailability(availabilityDetails.getDateAvailability());
            availability.setStartTime(availabilityDetails.getStartTime());
            availability.setEndTime(availabilityDetails.getEndTime());
            return availabilityRepository.save(availability);
        }).orElseThrow(()->new ResourceNotFoundException("Availability","Id",availabilityId));
    }

    @Override
    public ResponseEntity<?> deleteAvailability(Long productId, Long availabilityId) {
        return availabilityRepository.findByIdAndProductId(availabilityId,productId).map(availability -> {
            availabilityRepository.delete(availability);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Availability not found with Id"+availabilityId+"and ProductId"+productId));
    }
}
