package com.example.goblidas_backend.services;

import com.example.goblidas_backend.DTOs.CreateUserDTO;
import com.example.goblidas_backend.DTOs.RegisterDTO;
import com.example.goblidas_backend.entities.User;
import com.example.goblidas_backend.entities.enums.Role;
import com.example.goblidas_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {
    //@Autowired
    private UserRepository userRepository;

    //@Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(JpaRepository<User, Long> baseRepository, PasswordEncoder passwordEncoder, UserRepository userRepository){
        super(baseRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User createUser(User user){
        if (user.getPassword() != null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya esta en uso");
        }
        return userRepository.save(user);
    }

    public void registerUser(RegisterDTO dto) {
        if(userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya esta en uso");
        }

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setDni(dto.getDni());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.CUSTOMER);

        userRepository.save(user);
    }

    public Page<User> findAllPaged(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User fromDTO(CreateUserDTO dto) {
        if(userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya esta en uso");
        }

        User user = new User();
        user.setName(dto.getName());

        user.setEmail(dto.getEmail());
        user.setDni(dto.getDni());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.CUSTOMER);

        return user;
    }


}
