package com.example.goblidas_backend.services;

import com.example.goblidas_backend.entities.Size;
import com.example.goblidas_backend.repositories.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SizeService extends BaseService<Size> {

    @Autowired
    private SizeRepository sizeRepository;

    public SizeService(JpaRepository<Size, Long> baseRepository){
        super(baseRepository);
    }
}
