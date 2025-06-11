package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.Adress;
import com.example.goblidas_backend.services.AdressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/adress")
public class AdressController extends BaseController<Adress> {

    public AdressController(AdressService adressService){
        super(adressService);
    }
}
