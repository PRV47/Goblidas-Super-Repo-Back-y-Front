package com.example.goblidas_backend.repositories;

import com.example.goblidas_backend.entities.DetailImage;
import com.example.goblidas_backend.entities.DetailImageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailImageIdRepository extends JpaRepository<DetailImage, DetailImageId> {
}
