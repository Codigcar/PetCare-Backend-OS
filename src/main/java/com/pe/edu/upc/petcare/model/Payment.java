package com.pe.edu.upc.petcare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @javax.validation.constraints.NotNull(message = "the card number can't be empty")
    private Long cardNumber;


    @Column(name = "card_name",nullable = false)
    private String cardName;

    @javax.validation.constraints.NotNull(message = "the cvv number can't be empty")
    private Integer cvvNumber;

    @NotEmpty(message = "the expiration day can't be empty")
    @Column(name = "expiry_date",unique = true,nullable = false)
    private String expiryDate;

    //Relationships
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "provider_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Provider provider;


}
