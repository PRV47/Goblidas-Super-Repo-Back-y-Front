package com.example.goblidas_backend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    //public AuthResponse(String token) {
    //}

    //public AuthResponse(String token) {
    //    this.token = token;
    //}
}
