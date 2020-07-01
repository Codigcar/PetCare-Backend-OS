package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.model.Review;
import com.pe.edu.upc.petcare.repository.PersonProfileRepository;
import com.pe.edu.upc.petcare.repository.ProviderRepository;
import com.pe.edu.upc.petcare.repository.ReviewRepository;
import com.pe.edu.upc.petcare.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PersonProfileRepository personProfileRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Review create(Long personId, Long providerId, Review review) {
        review.setPersonProfile(personProfileRepository.findById(providerId).orElse(null));
        review.setProvider(providerRepository.findById(providerId).orElse(null));
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviewByProviderId(Long businessId,Long providerId) {
        return reviewRepository.getAllRequestByProviderId(providerId);
    }
}
