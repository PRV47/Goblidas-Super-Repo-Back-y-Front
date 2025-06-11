package com.example.goblidas_backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "descuento")
@Getter
@Setter
public class Discount extends Base {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "fecha_final")
    private LocalDateTime finalDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "fecha_inicio")
    private LocalDateTime initialDate;

    //Porcentaje como float (?
    @Column(name = "procentaje")
    private float percentage;
}
