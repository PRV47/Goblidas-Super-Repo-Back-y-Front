package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.Price;
import com.example.goblidas_backend.services.PriceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/price")
public class PriceController extends BaseController<Price> {

    public PriceController(PriceService priceService){
        super(priceService);
    }
}
