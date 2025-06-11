package com.example.goblidas_backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "detalle_imagen")
@Setter
@Getter
public class DetailImage {

    @EmbeddedId
    private DetailImageId id = new DetailImageId();

    @Column(name = "activo")
    private Boolean active = true;

    @ManyToOne
    @MapsId("detailId")
    @JoinColumn(name = "id_detalle", nullable = false)
    private Detail detailId;

    @ManyToOne
    @MapsId("imageId")
    @JoinColumn(name = "id_imagen", nullable = false)
    private Image imageId;





    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Detail getDetailId() {
        return detailId;
    }

    public void setDetailId(Detail detailId) {
        this.detailId = detailId;
    }

    public Image getImageId() {
        return imageId;
    }

    public void setImageId(Image imageId) {
        this.imageId = imageId;
    }

    public DetailImageId getId() {
        return id;
    }

    public void setId(DetailImageId id) {
        this.id = id;
    }
}
