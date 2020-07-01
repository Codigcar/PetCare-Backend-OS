package com.pe.edu.upc.petcare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

    @NotEmpty(message = "The startTime can't be empty")
    @Size(min = 5, max = 5, message = "the size of the start time is 5")
    @Column(name = "start_time")
    private String startTime;

    @NotEmpty(message = "The endTime can't be empty")
    @Size(min = 5, max = 5, message = "the size of the end time is 5")
    @Column(name = "end_time")
    private String endTime;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id")
    @JsonIgnore
    private Product product ;

}
