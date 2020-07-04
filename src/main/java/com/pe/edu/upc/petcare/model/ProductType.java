package com.pe.edu.upc.petcare.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Builder
@Table(name = "products_type")
@Data
public class ProductType {

    @Id
    private Long id;

    @NotBlank(message = "the type name can't be empty")
    @Column(name = "name",nullable = false)
    private String name;

    public ProductType(Long id,String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductType other = (ProductType) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    @Override
    public String toString() {

        var builder = new StringBuilder();
        builder.append("producttype{id=").append(id).append(", name=")
                .append(name);

        return builder.toString();
    }

}
