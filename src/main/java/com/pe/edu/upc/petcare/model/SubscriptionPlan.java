package com.pe.edu.upc.petcare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subscription_plan")
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int duration;

    private Double price;

    public SubscriptionPlan(String name, String description, int duration, double price) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
    }


    @Override
    public String toString() {

        var builder = new StringBuilder();
        builder.append("plan{id=").append(id).append(", name=")
                .append(name).append(", description")
                .append(description).append(", duration")
                .append(duration).append(", price").append(price);
        return builder.toString();
    }


}


