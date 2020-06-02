package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Account;
import com.pe.edu.upc.petcare.model.Review;
import com.pe.edu.upc.petcare.resource.AccountResource;
import com.pe.edu.upc.petcare.resource.ReviewResource;
import com.pe.edu.upc.petcare.resource.save.SaveReviewResource;
import com.pe.edu.upc.petcare.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/business/{businessId}/providers/{providerId}/reviews")
public class ProviderReviewController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ReviewService reviewService;

   @GetMapping
   public ResponseEntity<List<ReviewResource>> getAllReviewByProviderId(@PathVariable("businessId")Long businessId,
                                                                @PathVariable("providerId")Long providerId){
       List<Review> reviews = reviewService.getAllReviewByProviderId(businessId,providerId);
       return ResponseEntity.ok(reviews.stream().map(this::convertToResource).collect(Collectors.toList()));
   }



    private Review convertToEntity(SaveReviewResource resource) {

        return mapper.map(resource, Review.class);
    }

    private ReviewResource convertToResource(Review entity) {

        return mapper.map(entity, ReviewResource.class);
    }
}
