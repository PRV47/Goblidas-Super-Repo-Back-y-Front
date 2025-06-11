package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.Detail;
import com.example.goblidas_backend.entities.Image;
import com.example.goblidas_backend.services.DetailImageService;
import com.example.goblidas_backend.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/detail")
public class DetailController extends BaseController<Detail> {

    @Autowired
    private DetailImageService detailImageService;

    @Autowired
    private DetailService detailService;

    public DetailController(DetailService detailService, DetailImageService detailImageService){
        super(detailService);
        this.detailImageService = detailImageService;

    }

    @GetMapping("/{detailId}/images")
    public ResponseEntity<List<Image>> getImagesByDetail(@PathVariable Long detailId) throws Exception {
        Detail detail = detailService.findById(detailId);
        if (detail == null) {
            return ResponseEntity.notFound().build();
        }
        List<Image> images = detailImageService.getActiveImagesByDetail(detail);
        return ResponseEntity.ok(images);
    }
}
