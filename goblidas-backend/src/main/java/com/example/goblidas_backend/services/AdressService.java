package com.example.goblidas_backend.services;

import com.example.goblidas_backend.entities.Adress;
import com.example.goblidas_backend.repositories.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AdressService extends BaseService<Adress> {
    @Autowired
    private AdressRepository adressRepository;

    public AdressService(JpaRepository<Adress, Long> baseRepository){
        super(baseRepository);
    }

}
