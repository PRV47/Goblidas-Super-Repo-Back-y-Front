package com.example.goblidas_backend.DTOs;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String name;
    private String password;
    private String email;
    private String dni;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getDni() {
        return dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
