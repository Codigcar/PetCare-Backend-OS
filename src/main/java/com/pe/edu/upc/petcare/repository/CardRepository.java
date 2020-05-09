package com.pe.edu.upc.petcare.repository;


import com.pe.edu.upc.petcare.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {

    //Card findByNumberId(Long id);
//para cambiar port , edit configuration    Dserver.port=8080(puerto deseado)

}
