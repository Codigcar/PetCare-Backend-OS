package com.pe.edu.upc.petcare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "business_profile")
public class BusinessProfile extends Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "the first name can't be empty")
    @Column(name = "name",nullable = false)
    private String name;

    private String password;

    @NotEmpty(message = "the last name can't be empty")
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @NotEmpty(message = "the document identity document can't be empty")
    @Size(min = 8, max = 8, message = "the size of the identity document is 8")
    @Column(name = "document",unique = true,length = 8,nullable = false)
    private String document;

    @NotEmpty(message = "the email can't be empty")
    @Email(message = "it is not a valid email address")
    @Column(unique = true,nullable = false)
    private String email;

    @NotEmpty(message = "the phone number can't be empty")
    @Size(min = 9, max = 9, message = "the phone number size is 9")
    private String phone;

    @NotEmpty(message = "the age can't be empty")
    private String age;

    private boolean owner;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "provider_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Provider provider;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;

}
