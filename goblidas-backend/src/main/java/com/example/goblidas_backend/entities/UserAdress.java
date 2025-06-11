package com.example.goblidas_backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "direccion_usuario")
@Getter
@Setter
public class UserAdress extends Base {
    @ManyToOne
    @JoinColumn(name = "direccion_id", nullable = false)
    private Adress adressId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User userId;

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
