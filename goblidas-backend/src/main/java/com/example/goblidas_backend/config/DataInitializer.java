package com.example.goblidas_backend.config;

import com.example.goblidas_backend.entities.User;
import com.example.goblidas_backend.entities.enums.Role;
import com.example.goblidas_backend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if(userRepository.findByEmail("admin@goblidas.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@goblidas.com");
                admin.setPassword(passwordEncoder.encode("admin0321"));
                admin.setName("Admin");
                admin.setDni("46328818");
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);

                System.out.println("Admin creado");
            }
        };
    }
}
