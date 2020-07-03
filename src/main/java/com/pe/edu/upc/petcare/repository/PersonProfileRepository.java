package com.pe.edu.upc.petcare.repository;


import com.pe.edu.upc.petcare.model.PersonProfile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonProfileRepository extends JpaRepository<PersonProfile,Long> {
    //List<PersonProfile> findByProviderId (Long providerId, Pageable pageable);
}
