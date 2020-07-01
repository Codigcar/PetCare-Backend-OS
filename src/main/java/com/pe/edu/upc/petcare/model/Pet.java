package com.pe.edu.upc.petcare.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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

    @javax.validation.constraints.NotNull(message = "the age can't be empty")
    private Integer age;

    @NotEmpty(message = "the breed can't be empty")
    @Column(name = "breed",nullable = false)
    private String breed;

    @NotEmpty(message = "The photo can't be empty")
    @Column(unique = true,nullable = false)
    private String photo;

    @NotEmpty(message = "the gender can't be empty")
    @Column(nullable = false)
    private String gender;

    //Relationships
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "person_profile_Id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PersonProfile personProfile;


}