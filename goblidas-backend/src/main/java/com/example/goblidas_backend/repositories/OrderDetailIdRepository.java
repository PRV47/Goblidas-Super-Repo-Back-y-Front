package com.example.goblidas_backend.repositories;

import com.example.goblidas_backend.entities.OrderDetail;
import com.example.goblidas_backend.entities.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailIdRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
