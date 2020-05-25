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
@Table(name = "providerrepresentatives")
public class ProviderRepresentative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "the name can't be empty")
    @Column(name = "name",nullable = false)
    private String name;

    @NotEmpty(message = "the last name can't be empty")
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @NotEmpty(message = "the position can't be empty")
    @Column(name = "position",nullable = false)
    private String position;

    @NotEmpty(message = "the phone 1 can't be empty")
    @Column(name = "phone_1",nullable = false)
    private String phone1;

    @NotEmpty(message = "the phone 2 can't be empty")
    @Column(name = "phone_2",nullable = false)
    private String phone2;

    @NotEmpty(message = "the direction can't be empty")
    @Column(name = "direction",nullable = false)
    private String direction;

    //Relationships
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "provider_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Provider provider;
}
