package com.example.goblidas_backend.repositories;

import com.example.goblidas_backend.entities.Detail;
import com.example.goblidas_backend.entities.DetailImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailImageRepository extends JpaRepository<DetailImage, Long> {

    List<DetailImage> findByDetailIdAndActiveTrue(Detail detail);
}
