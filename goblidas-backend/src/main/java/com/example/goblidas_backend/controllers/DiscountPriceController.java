package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.DiscountPrice;
import com.example.goblidas_backend.services.DiscountPriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/discountprice")
public class DiscountPriceController extends BaseController<DiscountPrice> {

    private final DiscountPriceService discountPriceService;

    public DiscountPriceController(DiscountPriceService discountPriceService){
        super(discountPriceService);
        this.discountPriceService = discountPriceService;
    }

    @GetMapping("/price/{priceId}")
    public ResponseEntity<List<DiscountPrice>> getByPriceId(@PathVariable Long priceId) {
        try {
            System.out.println("GET /price/{priceId} - Buscando descuentos para priceId: " + priceId);
            List<DiscountPrice> discounts = discountPriceService.findByPriceId(priceId);
            System.out.println("Descuentos encontrados: " + discounts.size());
            return ResponseEntity.ok(discounts);
        } catch (Exception e) {
            System.out.println("Error en getByPriceId: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<DiscountPrice> create(@RequestBody DiscountPrice discountPrice) {
        try {
            System.out.println("POST / - Creando/actualizando descuento");
            System.out.println("Datos recibidos - PriceId: " + discountPrice.getPriceId().getId() + 
                             ", DiscountId: " + discountPrice.getDiscountId().getId() + 
                             ", Active: " + discountPrice.getActive());
            
            DiscountPrice result = discountPriceService.updateOrCreateDiscountPrice(discountPrice);
            System.out.println("Descuento creado/actualizado exitosamente");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.out.println("Error en create: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
