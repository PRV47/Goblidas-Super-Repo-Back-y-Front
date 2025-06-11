package com.example.goblidas_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categoria")
@Setter
@Getter
public class Category extends Base {
    @Column(name = "nombre")
    private String name;
}
