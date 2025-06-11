package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.Discount;
import com.example.goblidas_backend.services.DiscountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discount")
public class DiscountController extends BaseController<Discount> {

    public DiscountController(DiscountService discountService){
        super(discountService);
    }
}
