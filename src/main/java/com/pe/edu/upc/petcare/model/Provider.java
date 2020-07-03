package com.pe.edu.upc.petcare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "the business name can't be empty")
    @Column(name = "business_name",nullable = false)
    private String businessName;

    @NotEmpty(message = "the region can't be empty")
    @Column(name = "region",nullable = false)
    private String region;

    @NotEmpty(message = "the address can't be empty")
    @Column(name = "address",nullable = false)
    private String address;

    @NotEmpty(message = "the field can;t be empty")
    @Column(name = "field",nullable = false)
    private String field;

    @NotEmpty(message = "the email address can't be empty")
    @Email(message = "it is not a valid email address\"")
    @Column(name = "email",nullable = false)
    private String email;

    @NotEmpty(message = "the description can't be empty")
    @Column(name = "description",nullable = false)
    private String description;

    private int subscriptionPlan;

    private String photo;

}
