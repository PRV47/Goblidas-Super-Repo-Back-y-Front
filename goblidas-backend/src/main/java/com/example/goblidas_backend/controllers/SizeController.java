package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.Size;
import com.example.goblidas_backend.services.SizeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/size")
public class SizeController extends BaseController<Size> {

    public SizeController(SizeService sizeService){
        super(sizeService);
    }
}
