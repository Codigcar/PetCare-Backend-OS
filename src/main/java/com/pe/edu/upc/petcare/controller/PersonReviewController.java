package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Provider;
import com.pe.edu.upc.petcare.model.Review;
import com.pe.edu.upc.petcare.resource.ProviderResource;
import com.pe.edu.upc.petcare.resource.ReviewResource;
import com.pe.edu.upc.petcare.resource.save.SaveProviderResource;
import com.pe.edu.upc.petcare.resource.save.SaveReviewResource;
import com.pe.edu.upc.petcare.service.ProviderService;
import com.pe.edu.upc.petcare.service.ReviewService;
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
@RequestMapping("/api/people/{personId}/providers/{providerId}/reviews")
public class PersonReviewController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResource> SaveReviewByPerson(@PathVariable("personId") Long personId,
                                                             @PathVariable("providerId") Long providerId,
                                                             @Valid @RequestBody SaveReviewResource resource)
    {
        return ResponseEntity.ok(convertToResource(reviewService.create(personId,providerId,convertToEntity(resource)))) ;
    }



    private Review convertToEntity(SaveReviewResource resource) {

        return mapper.map(resource, Review.class);
    }

    private ReviewResource convertToResource(Review entity) {

        return mapper.map(entity, ReviewResource.class);
    }
}
