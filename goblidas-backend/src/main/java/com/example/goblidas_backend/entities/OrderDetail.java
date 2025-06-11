package com.example.goblidas_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pedido_detalle")
@Getter
@Setter
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id = new OrderDetailId();

    @Column(name = "activo")
    private Boolean active = true;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "id_pedido", nullable = false)
    @JsonBackReference
    private Order orderId;

    @ManyToOne
    @MapsId("detailId")
    @JoinColumn(name = "id_detalle", nullable = false)
    private Detail detailId;

    @Column(name = "cantidad")
    private Integer quantity;

    @Column(name = "precio_unitario")
    private BigDecimal unitPrice;

    public Detail getDetailId() {
        return detailId;
    }

    public void setDetailId(Detail detailId) {
        this.detailId = detailId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public OrderDetailId getId() {
        return id;
    }

    public void setId(OrderDetailId id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
