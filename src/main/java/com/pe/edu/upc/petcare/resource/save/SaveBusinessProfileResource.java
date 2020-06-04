package com.pe.edu.upc.petcare.resource.save;

import com.pe.edu.upc.petcare.model.Provider;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveBusinessProfileResource {

    private String name;

    private String password;


    private String lastName;

    private String document;

    private String email;


    private String phone;


    private String age;

    private boolean owner;
}
