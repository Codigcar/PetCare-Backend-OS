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

    @NotEmpty(message = "the first name can't be empty")
    private String firstName;

    @NotEmpty(message = "the last name can't be empty")
    private String lastName;

    @NotEmpty(message = "the document identity document can't be empty")
    @Size(min = 8, max = 8, message = "the size of the identity document is 8")
    private String document;

    @NotEmpty(message = "the email can't be empty")
    @Email(message = "it is not a valid email address")
    private String email;

    @NotEmpty(message = "the phone number can't be empty")
    private String phone;

    @NotEmpty(message = "the age can't be empty")
    private String age;

    private boolean owner;

    private Provider provider;
}
