package com.pe.edu.upc.petcare.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pe.edu.upc.petcare.model.ProductType;
import com.pe.edu.upc.petcare.model.Provider;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
public class ProviderJoinProductResource {

    private Long id;

    private Provider provider;


    private ProductType productType;
}
