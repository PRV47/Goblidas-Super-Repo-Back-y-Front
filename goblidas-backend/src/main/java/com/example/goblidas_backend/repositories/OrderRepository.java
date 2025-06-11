package com.example.goblidas_backend.repositories;

import com.example.goblidas_backend.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.userAdressId.userId.id = :userId")
    List<Order> findByUserAdressIdUserId_Id(@Param("userId") Long userId);
}
