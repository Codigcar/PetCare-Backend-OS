package com.pe.edu.upc.petcare.resource.save;

import com.pe.edu.upc.petcare.model.Provider;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class SavePersonRequestResource {

    private Date dateReservation;

    private String startTime;

    private String endTime;

    private String veterinaryName;

    private String productTypeName;

    private String productName;

    private String petName;

    private int status;

    private String PersonName;
}
