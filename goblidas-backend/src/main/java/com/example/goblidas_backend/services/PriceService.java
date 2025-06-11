package com.example.goblidas_backend.services;

import com.example.goblidas_backend.entities.Price;
import com.example.goblidas_backend.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PriceService extends BaseService<Price> {

    @Autowired
    private PriceRepository priceRepository;

    public PriceService(JpaRepository<Price, Long> baseRepository){
        super(baseRepository);
    }
}
