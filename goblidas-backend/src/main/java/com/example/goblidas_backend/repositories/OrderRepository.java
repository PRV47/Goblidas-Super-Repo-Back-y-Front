package com.example.goblidas_backend.repositories;

import com.example.goblidas_backend.entities.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {

    List<Order> findByUserAdressIdUserId_Id(Long userId);
}
