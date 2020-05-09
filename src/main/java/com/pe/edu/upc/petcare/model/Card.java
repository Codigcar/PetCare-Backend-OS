package com.pe.edu.upc.petcare.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotEmpty(message = "el numero no puede ser vacio")
    @Column(name = "number",nullable = false)
    private String number;


    @NotEmpty(message = "el nombre no puede ser vacio")
    @Column(name = "name",nullable = false)
    private String name;

    @NotEmpty(message = "el numero cvv no puede ser vacio")
    @Column(name = "cvv_number",nullable = false)
    private String cvv_number;

    @NotEmpty(message = "El dia de expiracion no puede ser vacio")
    @Size(min = 8, max = 8, message = "el tamano del documento es 8")
    @Column(name = "expiry_date",unique = true,length = 8,nullable = false)
    private String expiry_date;



}