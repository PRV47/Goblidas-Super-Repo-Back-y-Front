package com.example.goblidas_backend.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DetailImageId implements Serializable {

    private Long detailId;
    private Long imageId;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
