package com.example.goblidas_backend.entities;

import com.example.goblidas_backend.entities.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class User extends Base implements UserDetails {

    @NonNull
    @Column(name = "nombre")
    private String name;

    @Column(name = "contraseña")
    @JsonIgnore
    private String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Role role = Role.CUSTOMER;

    @Column(name = "email")
    private String email;

    @Column(name = "dni")
    private String dni;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
