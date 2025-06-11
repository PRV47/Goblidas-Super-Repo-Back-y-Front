package com.example.goblidas_backend.services;

import com.example.goblidas_backend.entities.DiscountPriceId;
import com.example.goblidas_backend.repositories.DiscountPriceIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DiscountPriceIdService extends BaseService<DiscountPriceId> {

    @Autowired
    private DiscountPriceIdRepository discountPriceIdRepository;

    public DiscountPriceIdService(JpaRepository<DiscountPriceId, Long> baseRepository){
        super(baseRepository);
    }
}
