package com.example.goblidas_backend.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderDetailId implements Serializable {
    private Long orderId;
    private Long detailId;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


}
