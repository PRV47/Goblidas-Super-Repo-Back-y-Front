package com.example.goblidas_backend.repositories;
import com.example.goblidas_backend.entities.Detail;
import com.example.goblidas_backend.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends BaseRepository<Detail, Long> {
    @Query("SELECT d FROM Detail d WHERE d.productIdj = :product AND d.active = true AND d.state = true")
    List<Detail> findActiveAndInStockByProduct(@Param("product") Product product);
}
