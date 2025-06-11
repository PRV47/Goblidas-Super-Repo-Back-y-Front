package com.example.goblidas_backend.entities;

import com.example.goblidas_backend.repositories.BaseRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

@Entity
@Table(name = "precio_con_descuento")
@Getter
@Setter
public class DiscountPrice {


    @EmbeddedId
    private DiscountPriceId id = new DiscountPriceId();

    @Column(name = "activo")
    private Boolean active = true;

    @ManyToOne
    @MapsId("discountId")
    @JoinColumn(name = "id_descuento", nullable = false)
    private Discount discountId;

    @ManyToOne
    @MapsId("priceId")
    @JoinColumn(name = "id_precio", nullable = false)
    private Price priceId;

}
