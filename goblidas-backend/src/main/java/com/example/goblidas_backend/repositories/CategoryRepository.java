package com.example.goblidas_backend.repositories;

import com.example.goblidas_backend.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
}
