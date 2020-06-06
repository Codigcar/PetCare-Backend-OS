package com.pe.edu.upc.petcare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "medical_record")
public class MedicalRecord extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "the description can't be empty")
    @Column(name = "description",nullable = false)
    private String description;

    @NotEmpty(message = "the type can't be empty")
    @Column(name = "type",nullable = false)
    private String type;

    @NotEmpty(message = "the action can't be empty")
    @Column(name = "action",nullable = false)
    private String action;

    //RelationShip
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "medicalprofile_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MedicalProfile medicalProfile;

}
