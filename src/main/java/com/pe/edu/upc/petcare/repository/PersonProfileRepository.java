package com.pe.edu.upc.petcare.repository;


import com.pe.edu.upc.petcare.model.PersonProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonProfileRepository extends JpaRepository<PersonProfile,Long> {
}
