package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.Category;
import com.example.goblidas_backend.services.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController<Category> {

    public CategoryController(CategoryService categoryService){
        super(categoryService);
    }
}
