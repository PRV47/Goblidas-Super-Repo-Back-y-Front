package com.example.goblidas_backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "producto")
@Getter
@Setter
public class Product extends Base {

    //No se si este tipo este bien
    @Column(name = "tipo_producto")
    private String productType;

    @Column(name = "nombre")
    private String name;

    @Column(name = "sexo")
    private String gender;

    //@ManyToOne
    //@JoinColumn(name = "id_categoria", nullable = false)
    //private Category categoryId;

    @Column(name = "destacado")
    private Boolean highlighted;

    public Boolean getHighlighted() {
        return highlighted;
    }

    public void setHighlighted(Boolean highlighted) {
        this.highlighted = highlighted;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(
            name = "producto_categoria",
            joinColumns = @JoinColumn(name = "id_producto"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Category> categoriesIds = new ArrayList<>();

    @OneToMany(mappedBy = "productIdj", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Detail> details = new ArrayList<>();
}
