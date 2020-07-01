package com.pe.edu.upc.petcare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "the name can't be empty")
    @Column(name = "name",nullable = false)
    private String name;

    public Rol(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        var builder = new StringBuilder();
        builder.append("rol{id=").append(id).append(", name=")
                .append(name);

        return builder.toString();
    }


}
