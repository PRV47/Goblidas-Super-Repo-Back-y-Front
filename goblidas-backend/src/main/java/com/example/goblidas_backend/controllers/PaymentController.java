package com.example.goblidas_backend.controllers;


import com.example.goblidas_backend.DTOs.CartItemDTO;
import com.example.goblidas_backend.services.OrderService;
import com.example.goblidas_backend.services.PaymentService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.MercadoPagoClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.resources.preference.PreferenceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    public PaymentController(PaymentService paymentService) throws MPException {

        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPreference(@RequestBody Long orderId) throws MPException, MPApiException {
        String initPoint = paymentService.createPreferenceFromOrder(orderId);
        return ResponseEntity.ok(initPoint);

    }

    @GetMapping("/failure")
    public ResponseEntity<String> paymentFailure(@RequestParam("external_reference") Long orderId) throws Exception {
        orderService.revertStock(orderId);
        //orderService.cancelOrder(orderId);

        return ResponseEntity.ok("Pago fallido, se restauro el stock");

    }

    @GetMapping("/success")
    public ResponseEntity<String> paymentSuccess(@RequestParam("external_reference") Long orderId) throws Exception {
        orderService.markOrderAsPaid(orderId);

        return ResponseEntity.ok("Pago exitoso!");
    }





}
