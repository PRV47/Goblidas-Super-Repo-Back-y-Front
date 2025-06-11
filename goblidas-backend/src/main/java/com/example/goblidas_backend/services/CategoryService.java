package com.example.goblidas_backend.services;

import com.example.goblidas_backend.entities.Category;
import com.example.goblidas_backend.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(JpaRepository<Category, Long> baseRepository){
        super(baseRepository);
    }
}
