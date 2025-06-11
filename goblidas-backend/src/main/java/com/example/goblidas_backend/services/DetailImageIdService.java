package com.example.goblidas_backend.services;

import com.example.goblidas_backend.entities.DetailImageId;
import com.example.goblidas_backend.repositories.DetailImageIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DetailImageIdService extends BaseService<DetailImageId> {

    @Autowired
    private DetailImageIdRepository detailImageIdRepository;

    public DetailImageIdService(JpaRepository<DetailImageId, Long> baseRepository){
        super(baseRepository);
    }
}
