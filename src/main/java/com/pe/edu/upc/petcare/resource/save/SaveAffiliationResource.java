package com.pe.edu.upc.petcare.resource.save;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Provider;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveAffiliationResource {


    //private PersonProfile person; // dato del parametro

    private Long providerId; // id ingresará la persona

    //private Provider provider; // dato obtenido por id

}
