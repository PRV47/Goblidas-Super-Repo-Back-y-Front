package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.Detail;
import com.example.goblidas_backend.entities.DetailImage;
import com.example.goblidas_backend.entities.DetailImageId;
import com.example.goblidas_backend.entities.Image;
import com.example.goblidas_backend.repositories.DetailImageRepository;
import com.example.goblidas_backend.repositories.DetailRepository;
import com.example.goblidas_backend.repositories.ImageRepository;
import com.example.goblidas_backend.services.CloudinaryService;
import com.example.goblidas_backend.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private DetailImageRepository detailImageRepository;

    public ImageController(
            ImageService imageService,
            ImageRepository imageRepository,
            CloudinaryService cloudinaryService,
            DetailRepository detailRepository,
            DetailImageRepository detailImageRepository
    ){
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.cloudinaryService = cloudinaryService;
        this.detailRepository = detailRepository;
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,
                                        @RequestParam("detailId") Long detailId) {
        try {
            String imageUrl = cloudinaryService.uploadImage(file);

            Image image = new Image();
            image.setUrl(imageUrl);
            Image savedImage = imageRepository.save(image);

            Detail detail = detailRepository.findById(detailId).orElseThrow(() -> new RuntimeException("No se encontro el detalle id"));

            DetailImage detailImage = new DetailImage();
            detailImage.setDetailId(detail);
            detailImage.setImageId(savedImage);

            DetailImageId detailImageId = new DetailImageId();
            detailImageId.setDetailId(detail.getId());
            detailImageId.setImageId(savedImage.getId());
            detailImage.setId(detailImageId);

            detailImageRepository.save(detailImage);

            return ResponseEntity.ok().body(imageUrl);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al subir imagen: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAll() {
        try {
            List<Image> entities = imageService.findAll();
            return ResponseEntity.ok(entities);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getById(@PathVariable Long id){
        try {
            Image entity = imageService.findById(id);
            if(entity != null){
                return ResponseEntity.ok(entity);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> update(@PathVariable Long id, @RequestBody Image entity){
        try {
            Image entityFound = imageService.findById(id);
            System.out.println(entityFound);
            Image entityUpdated = imageService.update(id, entityFound);
            if(entityUpdated == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(imageService.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try {
            Image image = imageService.findById(id);

            if (image != null) {
               image.setActive(false);
               imageService.save(image);

               return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }

            //if(imageService.delete(id)) {
            //    return ResponseEntity.ok().build();
            //} else {
            //    return ResponseEntity.notFound().build();
            //}
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Image>> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Image> images = imageService.findAllPaged(pageable);
        return ResponseEntity.ok(images);
    }
}
