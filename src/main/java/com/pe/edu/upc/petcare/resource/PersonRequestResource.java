package com.pe.edu.upc.petcare.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
public class PersonRequestResource {

    private Long id;

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
