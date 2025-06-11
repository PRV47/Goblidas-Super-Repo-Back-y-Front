package com.example.goblidas_backend.services;

import com.example.goblidas_backend.entities.OrderDetailId;
import com.example.goblidas_backend.repositories.OrderDetailIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailIdService extends BaseService<OrderDetailId> {

    @Autowired
    private OrderDetailIdRepository orderDetailIdRepository;

    public OrderDetailIdService(JpaRepository<OrderDetailId, Long> baseRepository){
        super(baseRepository);
    }
}
