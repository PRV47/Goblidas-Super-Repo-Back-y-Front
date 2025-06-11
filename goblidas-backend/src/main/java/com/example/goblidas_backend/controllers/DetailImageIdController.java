package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.DetailImageId;
import com.example.goblidas_backend.services.DetailImageIdService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detailimageid")
public class DetailImageIdController extends BaseController<DetailImageId> {

    public DetailImageIdController(DetailImageIdService detailImageIdService){
        super(detailImageIdService);
    }
}
