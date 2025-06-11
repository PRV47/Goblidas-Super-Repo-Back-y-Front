package com.example.goblidas_backend.services;

import com.example.goblidas_backend.entities.Discount;
import com.example.goblidas_backend.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DiscountService extends BaseService<Discount> {

    @Autowired
    private DiscountRepository discountRepository;

    public DiscountService(JpaRepository<Discount, Long> baseRepository){
        super(baseRepository);
    }
}
