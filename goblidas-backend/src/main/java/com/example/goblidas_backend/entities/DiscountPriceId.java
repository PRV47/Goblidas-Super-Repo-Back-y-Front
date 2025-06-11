package com.example.goblidas_backend.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DiscountPriceId implements Serializable {

    private Long discountId;
    private Long priceId;

}
