package com.example.goblidas_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "precio")
@Getter
@Setter
public class Price extends Base {
    @Column(name = "precio_compra")
    private Double purchasePrice;

    @Column(name = "precio_venta")
    private Double sellingPrice;
}
