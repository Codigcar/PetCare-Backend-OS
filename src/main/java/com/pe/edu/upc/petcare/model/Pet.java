package com.pe.edu.upc.petcare.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "the name can't be empty")
    @Column(name = "name",nullable = false)
    private String name;

    @NotEmpty(message = "the age can't be empty")
    private String age;

    @NotEmpty(message = "the breed can't be empty")
    @Column(name = "breed",unique = true,nullable = false)
    private String breed;

    @NotEmpty(message = "The photo can't be empty")
    @Column(unique = true,nullable = false)
    private String photo;

    @NotEmpty(message = "the gender can't be empty")
    @Column(unique = true,nullable = false)
    private String gender;

    //Relationships
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "customer_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;


}