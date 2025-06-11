package com.example.goblidas_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "talle")
@Setter
@Getter
public class Size extends Base {

    //No se si byte sea el tipo adecuado
    @Column(name = "numero")
    private Integer number;
}
