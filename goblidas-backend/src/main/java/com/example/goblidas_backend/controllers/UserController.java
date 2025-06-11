package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.DTOs.CreateUserDTO;
import com.example.goblidas_backend.entities.User;
import com.example.goblidas_backend.entities.enums.Role;
import com.example.goblidas_backend.repositories.UserRepository;
import com.example.goblidas_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository){

        this.userService = userService;
        this.userRepository = userRepository;
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO dto){
        //if(user.getRole() == null){
        //    user.setRole(Role.CUSTOMER);
        //}

        try {

            User newUser = userService.fromDTO(dto);
            //created.setPassword(null);
            userRepository.save(newUser);
            //return new ResponseEntity<>(created, HttpStatus.CREATED);

            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> entities = userService.findAll();
            return ResponseEntity.ok(entities);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        try {
             User entity = userService.findById(id);
            if(entity != null){
                return ResponseEntity.ok(entity);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User entity){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User loggedInUser = (User) auth.getPrincipal();

            User entityFound = userService.findById(id);

            if (!loggedInUser.getRole().equals(Role.ADMIN)
                    && !entity.getRole().equals(entityFound.getRole())
                    && entity.getName().equals(entityFound.getName())
                    && entity.getEmail().equals(entityFound.getEmail())
                    && entity.getDni().equals(entityFound.getDni())) {
                //entity.setRole(entityFound.getRole()); // forzar a mantener el rol actual si no es admin
                Map<String, String> body = new HashMap<>();
                body.put("error", "No tiene permisos para cambiar el rol del usuario.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
            }

            if(entityFound == null){
                return ResponseEntity.notFound().build();
            }

            System.out.println(entityFound);
            entityFound.setName(entity.getName());
            entityFound.setEmail(entity.getEmail());
            entityFound.setDni(entity.getDni());
            // ⚠️ Solo permitir modificar el rol si sos ADMIN
            entityFound.setRole(entity.getRole());


            User entityUpdated = userService.update(id, entityFound);

            if(entityUpdated == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(userService.update(id, entityUpdated));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try {
            if(userService.delete(id)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile() {
        return ResponseEntity.ok("Perfil de usuario autenticado");
    }


    @GetMapping("/paged")
    public ResponseEntity<Page<User>> getAllPagedUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userService.findAllPaged(pageable);
        return ResponseEntity.ok(users);
    }
}
