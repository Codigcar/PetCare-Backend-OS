package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Review;

import java.util.List;

public interface ReviewService {
    Review create(Long personId,Long providerId, Review review);
    List<Review> getAllReviewByProviderId(Long businessId,Long providerId);
}
