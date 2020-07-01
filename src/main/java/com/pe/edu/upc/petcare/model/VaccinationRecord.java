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

@Table(name = "vaccination_record")

public class VaccinationRecord extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @NotEmpty(message = "the name can't be empty")
    @Column(name = "name",nullable = false)
    private String name ;

    @NotEmpty(message = "the description can't be empty")
    @Column(name = "description",nullable = false)
    private String description ;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "medicalprofile_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MedicalProfile medicalProfile;


}
