package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("select r from Review r where r.provider.id = ?1")
    List<Review> getAllRequestByProviderId(Long providerId);
}
