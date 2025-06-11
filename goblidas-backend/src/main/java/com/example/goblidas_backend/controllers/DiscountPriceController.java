package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.DiscountPrice;
import com.example.goblidas_backend.services.DiscountPriceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discountprice")
public class DiscountPriceController extends BaseController<DiscountPrice> {

    public DiscountPriceController(DiscountPriceService discountPriceService){
        super(discountPriceService);
    }
}
