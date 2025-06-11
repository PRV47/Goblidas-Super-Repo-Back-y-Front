package com.example.goblidas_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "detalle")
@Getter
@Setter
@Data
public class Detail extends Base {
    @Column(name = "estado")
    private Boolean state;

    @Column(name = "color")
    private String colour;

    //No se si estos dos tipos sean correctos
    @Column(name = "marca")
    private String brand;

    @Column(name = "stock")
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "id_precio", nullable = false)
    private Price prizeId;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    @JsonBackReference
    private Product productIdj;

    @ManyToOne
    @JoinColumn(name = "id_talle", nullable = false)
    private Size sizeId;

    public void setProductIdj(Product productIdj) {
        this.productIdj = productIdj;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Product getProductIdj() {
        return productIdj;
    }


}
