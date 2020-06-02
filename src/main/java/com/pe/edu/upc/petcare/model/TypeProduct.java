package com.pe.edu.upc.petcare.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "type_products")
@Data
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "the type name can't be empty")
    @Column(name = "name",nullable = false)
    private String name;

}
