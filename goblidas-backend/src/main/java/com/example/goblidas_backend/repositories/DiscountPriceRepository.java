package com.example.goblidas_backend.repositories;

import com.example.goblidas_backend.entities.DiscountPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiscountPriceRepository extends JpaRepository<DiscountPrice, Long> {
    List<DiscountPrice> findByPriceIdId(Long priceId);
}
