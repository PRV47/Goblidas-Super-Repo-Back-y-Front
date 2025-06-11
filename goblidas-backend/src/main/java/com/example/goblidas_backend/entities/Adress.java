package com.example.goblidas_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "direccion")
@Getter
@Setter
public class Adress extends Base {
   @Column(name = "departamento")
   private String departament;

   @Column(name = "localidad")
   private String locality;

   @Column(name = "pais")
   private String country;

   @Column(name = "provincia")
   private String province;

   @Column(name = "numero")
   private Integer number;

   @Column(name = "calle")
   private String streetName;
}
