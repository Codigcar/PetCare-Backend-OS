package com.pe.edu.upc.petcare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "availability")
@Getter
@Setter
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The availability date can't be empty")
    @Column(name = "date_availability")
    private String dateAvailability;

    @NotEmpty(message = "The availability date can't be empty")
    @Temporal(TemporalType.TIME)
    @Column(name = "start_time")
    private Date startTime;

    @NotEmpty(message = "The availability date can't be empty")
    @Temporal(TemporalType.TIME)
    @Column(name = "end_time")
    private Date endTime;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "product_id",nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

}
