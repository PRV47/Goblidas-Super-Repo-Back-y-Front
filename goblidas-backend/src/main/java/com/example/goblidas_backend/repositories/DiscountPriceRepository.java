package com.example.goblidas_backend.repositories;

import com.example.goblidas_backend.entities.DiscountPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountPriceRepository extends JpaRepository<DiscountPrice, Long> {
}
