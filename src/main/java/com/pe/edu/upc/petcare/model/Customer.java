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
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "el nombre no puede ser vacio")
    @Column(name = "first_name",nullable = false)
    private String firstname;

    @NotEmpty(message = "el apellido no puede ser vacio")
    @Column(name = "last_name",nullable = false)
    private String lastname;

    @NotEmpty(message = "El numero de documento no puede ser vacio")
    @Size(min = 8, max = 8, message = "el tamano del documento es 8")
    @Column(name = "document",unique = true,length = 8,nullable = false)
    private String document;

    @NotEmpty(message = "el correo no puede ser vacio")
    @Email(message = "no es un direccion de correo bien formada")
    @Column(unique = true,nullable = false)
    private String email;

    @NotEmpty(message = "el telefono no puede ser vacio")
    @Size(min = 9, max = 9, message = "el tamano del telefono es 9")
    private String Phone;

    @NotEmpty(message = "la edad no puede ser vacio")
    private String Age;


}