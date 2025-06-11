package com.example.goblidas_backend.services;

import com.example.goblidas_backend.entities.DiscountPrice;
import com.example.goblidas_backend.repositories.DiscountPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DiscountPriceService extends BaseService<DiscountPrice> {

    @Autowired
    private DiscountPriceRepository discountPriceRepository;

    public DiscountPriceService(JpaRepository<DiscountPrice, Long> baseRepository){
        super(baseRepository);
    }
}
