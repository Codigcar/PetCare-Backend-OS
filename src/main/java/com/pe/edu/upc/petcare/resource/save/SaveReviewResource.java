package com.pe.edu.upc.petcare.resource.save;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Provider;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class SaveReviewResource {

    private String commentary;

    private int qualification;


}
