package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.BusinessProfile;
import com.pe.edu.upc.petcare.model.PersonRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRequestRepository extends JpaRepository<PersonRequest,Long> {

    @Query("SELECT p from PersonRequest p where p.product.id = ?1")
    List<PersonRequest> getAllByProductId(Long productId);
}
