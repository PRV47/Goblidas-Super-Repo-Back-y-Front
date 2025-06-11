package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.Detail;
import com.example.goblidas_backend.entities.DetailImage;
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
@RequestMapping("/api/detailimage")
public class DetailImageController extends BaseController<DetailImage> {

    public DetailImageController(DetailImageService detailImageService, DetailService detailService){
        super(detailImageService);

    }


}
