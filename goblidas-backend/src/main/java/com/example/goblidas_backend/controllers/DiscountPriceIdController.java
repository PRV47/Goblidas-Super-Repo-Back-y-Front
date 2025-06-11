package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.DiscountPriceId;
import com.example.goblidas_backend.services.DiscountPriceIdService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discountpriceid")
public class DiscountPriceIdController extends BaseController<DiscountPriceId> {

    public DiscountPriceIdController(DiscountPriceIdService discountPriceIdService){
        super(discountPriceIdService);
    }
}
