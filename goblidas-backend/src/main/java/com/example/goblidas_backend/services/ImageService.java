package com.example.goblidas_backend.services;

import com.example.goblidas_backend.entities.Image;
import com.example.goblidas_backend.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService extends BaseService<Image> {

    @Autowired
    private ImageRepository imageRepository;

    public ImageService(JpaRepository<Image, Long> baseRepository){
        super(baseRepository);
    }

    public Page<Image> findAllPaged(Pageable pageable){
        return imageRepository.findAll(pageable);
    }

}
