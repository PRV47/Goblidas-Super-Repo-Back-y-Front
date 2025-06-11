package com.example.goblidas_backend.repositories;

import com.example.goblidas_backend.entities.Order;
import com.example.goblidas_backend.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
