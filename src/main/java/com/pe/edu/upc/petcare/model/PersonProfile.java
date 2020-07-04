package com.pe.edu.upc.petcare.model;


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
@Table(name = "person_profile")
public class PersonProfile extends Profile {

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

    @javax.validation.constraints.NotNull(message = "the document can't be empty")
    private Long document;

    @NotEmpty(message = "the email can't be empty")
    @Email(message = "it is not a valid email address")
    @Column(unique = true,nullable = false)
    private String email;

    @javax.validation.constraints.NotNull(message = "the age can't be empty")
    private Integer age;

    @javax.validation.constraints.NotNull(message = "phone can't be empty")
    private Long phone;


    private String photo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;
    
}