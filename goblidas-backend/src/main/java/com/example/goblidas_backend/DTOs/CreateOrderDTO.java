package com.example.goblidas_backend.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDTO {
    private Long userAdressId;
    private List<CartItemDTO> cartItems;

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setUserAdressId(Long userAdressId) {
        this.userAdressId = userAdressId;
    }

    public Long getUserAdressId() {
        return userAdressId;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
