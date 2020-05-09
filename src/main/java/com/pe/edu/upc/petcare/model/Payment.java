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
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "el numero de la tarjeta no puede estar vacio")
    @Size(min = 16, max = 16, message = "el tamano del documento es 16")
    @Column(name = "card_number",unique = true,length = 16,nullable = false)
    private String card_number;

    @NotEmpty(message = "el nombre de la tarjeta no puede ser vacio")
    @Column(name = "name",nullable = false)
    private String name;

    @NotEmpty(message = "El numero de cvv no puede ser vacio")
    @Size(min = 3, max = 3, message = "el tamano del cvv es 3")
    @Column(name = "cvv_number",unique = true,length = 3,nullable = false)
    private String cvv_number;

    @NotEmpty(message = "la fecha de expiracion no puede ser vacia")
    @Column(name = "expiry_date",unique = true,nullable = false)
    private String expiry_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCvv_number() {
        return cvv_number;
    }

    public void setCvv_number(String cvv_number) {
        this.cvv_number = cvv_number;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }
}
