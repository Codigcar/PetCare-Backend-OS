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
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "medicalprofile")
public class MedicalProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @NotEmpty(message = "the name can't be empty")
    @Column(name = "name",nullable = false)
    private String Name ;

    @NotEmpty(message = "the weight can't be empty")
    @Column(name = "weight",nullable = false)
    private String Weight ;

    @NotEmpty(message = "the height can't be empty")
    @Column(name = "height",nullable = false)
    private String Height ;

    @NotEmpty(message = "the height can't be empty")
    @Column(name = "lenght",nullable = false)
    private String Lenght ;

    @NotEmpty(message = "the eyes can't be empty")
    @Column(name = "eyes",nullable = false)
    private String Eyes ;

    @NotEmpty(message = "the breed can't be empty")
    @Column(name = "breed",nullable = false)
    private String Breed ;

    @NotEmpty(message = "the sex can't be empty")
    @Column(name = "gender",nullable = false)
    private String Gender;

    @NotEmpty(message = "the color can't be empty")
    @Column(name = "color",nullable = false)
    private String Color;

    @NotEmpty(message = "the description can't be empty")
    @Column(name = "description",nullable = false)
    @Lob
    private String Description ;

    @NotEmpty(message = "The photo can't be empty")
    @Column(name = "photo",unique = true,nullable = false)
    private String Photo ;

    @NotEmpty(message = "the age can't be empty")
    @Column(name = "age",nullable = false)
    private String Age ;

    // private MedicalRecord medicalrecord ;
    // private int ServicesMedicalRecordForeignKey ;

    //Relationships

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pet_id")
    @JsonIgnore
    private Pet pet ;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="provider_id")
    @JsonIgnore
    private Provider provider ;







}
