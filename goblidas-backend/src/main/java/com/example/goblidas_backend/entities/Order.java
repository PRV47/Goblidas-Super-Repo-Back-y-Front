package com.example.goblidas_backend.entities;

import com.example.goblidas_backend.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@Data
public class Order extends Base {
    @Column(name = "total")
    private Float summary;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @Column(name = "fecha")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_usuario_direccion", nullable = false)
    private UserAdress userAdressId;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Float getSummary() {
        return summary;
    }

    public void setSummary(Float summary) {
        this.summary = summary;
    }

    public UserAdress getUserAdressId() {
        return userAdressId;
    }

    public void setUserAdressId(UserAdress userAdressId) {
        this.userAdressId = userAdressId;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
