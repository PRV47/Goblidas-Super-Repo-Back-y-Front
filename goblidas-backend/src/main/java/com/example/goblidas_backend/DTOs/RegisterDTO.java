package com.example.goblidas_backend.DTOs;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RegisterDTO {
    private String name;
    private String email;
    private String password;
    private String dni;
    //private String role;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    //public String getRole() {
    //    return role;
    //}

    //public void setRole(String role) {
    //    this.role = role;
    //}

    public void setPassword(String password) {
        this.password = password;
    }
}
