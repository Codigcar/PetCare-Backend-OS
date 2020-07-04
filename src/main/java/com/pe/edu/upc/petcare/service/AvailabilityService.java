package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Availability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AvailabilityService {

        Page<Availability> getAllAvailabilitiesByProductId(Long productId, Pageable pageable);
        Availability createAvailability (Long productId,Availability availability);
        Availability updateAvailability (Long productId, Long availabilityId,Availability availabilityDetails);
        ResponseEntity<?> deleteAvailability(Long productId,Long availabilityId);
}
