package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.User;
import com.example.goblidas_backend.entities.enums.Role;
import com.example.goblidas_backend.repositories.UserRepository;
import com.example.goblidas_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public AdminController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> getDashboard() {
        return ResponseEntity.ok("Dashboard de admin");
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUserFromAdmin(@RequestBody User user) {
        try {
            if (user.getRole() != null) {
                user.setRole(Role.valueOf(user.getRole().name().toUpperCase()));
            }
            User newUser = userService.createUser(user);
            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
