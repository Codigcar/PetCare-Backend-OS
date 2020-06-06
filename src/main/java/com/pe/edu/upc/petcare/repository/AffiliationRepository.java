package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.Affiliation;
import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AffiliationRepository extends JpaRepository<Affiliation,Long> {
  //  @Query("SELECT a FROM Affiliation a WHERE a.provider = ?1")
    @Query("SELECT p FROM PersonProfile p JOIN Affiliation  a on p.id = a.person.id where a.provider = ?1")
    List<PersonProfile> findByProvider(Provider provider);
}
