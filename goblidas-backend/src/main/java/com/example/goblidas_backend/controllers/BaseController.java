package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.services.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<E> {

    protected BaseService<E> baseService;

    public BaseController(BaseService<E> baseService){
        this.baseService = baseService;
    }

    @GetMapping
    public ResponseEntity<List<E>> getAll() {
        try {
            List<E> entities = baseService.findAll();
            return ResponseEntity.ok(entities);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<E> getById(@PathVariable Long id){
        try {
            E entity = baseService.findById(id);
            if(entity != null){
                return ResponseEntity.ok(entity);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<E> create(@RequestBody E entity){
        try {
            E newEntity = baseService.save(entity);
            return ResponseEntity.status(201).body(newEntity);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<E> update(@PathVariable Long id, @RequestBody E entity){
        try {
            E entityFound = baseService.findById(id);
            System.out.println(entityFound);
            E entityUpdated = baseService.update(id, entityFound);
            if(entityUpdated == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(baseService.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try {
            if(baseService.delete(id)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<E>> findAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<E> result = baseService.findAll(pageable);
        return ResponseEntity.ok(result);
    }


}
