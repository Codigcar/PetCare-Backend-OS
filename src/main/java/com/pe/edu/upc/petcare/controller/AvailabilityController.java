package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Availability;
import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.resource.AvailabilityResource;
import com.pe.edu.upc.petcare.resource.PetResource;
import com.pe.edu.upc.petcare.resource.save.SaveAvailabilityResource;
import com.pe.edu.upc.petcare.resource.save.SavePetResource;
import com.pe.edu.upc.petcare.service.AvailabilityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/business/{businessId}/providers/{providerId}/product-types/{producttypeId}/products/{productId}/availabilities")
public class AvailabilityController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping
    public Page<AvailabilityResource> getAllAvailabilitiesByCustomerId(@PathVariable(name = "productId")Long productId,
                                                    Pageable pageable){
        Page<Availability> availabilityPage=availabilityService.getAllAvailabilitiesByProductId(productId,pageable);
        List<AvailabilityResource> resources=availabilityPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @PostMapping
    public AvailabilityResource createAvailability(@PathVariable(name = "productId")Long productId,
                                 @Valid @RequestBody SaveAvailabilityResource resource){
        return convertToResource(availabilityService.createAvailability(productId,convertToEntity(resource)));
    }

    @PutMapping("/{availabilityId}")
    public AvailabilityResource updateAvailability(@PathVariable(name = "productId")Long productId,
                                 @PathVariable(name = "availabilityId")Long availabilityId,
                                 @Valid @RequestBody SaveAvailabilityResource resource){
        return convertToResource(availabilityService.updateAvailability(productId,availabilityId,convertToEntity(resource)));
    }

    private Availability convertToEntity(SaveAvailabilityResource resource) {
        return mapper.map(resource, Availability.class);
    }

    private AvailabilityResource convertToResource(Availability entity) {
        return mapper.map(entity, AvailabilityResource.class);
    }
}
