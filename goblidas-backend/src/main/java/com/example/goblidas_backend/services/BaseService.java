package com.example.goblidas_backend.services;

import com.example.goblidas_backend.entities.Base;
import com.example.goblidas_backend.repositories.BaseRepository;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<E> {

    protected JpaRepository<E, Long> baseRepository;

    public BaseService(JpaRepository<E, Long> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Transactional
    public List<E> findAll() throws Exception {
        try {
            return baseRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public E findById(Long id) throws Exception {
        try {
            Optional<E> entity = baseRepository.findById(id);
            if(entity.isPresent()) {
                return entity.get();
            } else {
                throw new Exception("Entidad no encontrada");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public E save(E entity) throws Exception {
        try {
            return baseRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public E update(Long id, E entity) throws Exception {
        try {
            if(baseRepository.existsById(id)) {
                return baseRepository.save(entity);
            } else {
                throw new Exception("Entidad no encontrada, no se puede actualizar");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean delete(Long id) throws Exception {
        try {
            if(baseRepository.existsById(id)) {
                Optional<E> entity = baseRepository.findById(id);
                if (entity.isPresent()) {

                    E entityTo = entity.get();
                    ((Base) entityTo).setActive(false);
                    baseRepository.save(entityTo);
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return false;
    }

    public Page<E> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }
}
