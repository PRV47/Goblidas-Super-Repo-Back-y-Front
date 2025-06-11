package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.services.WebhookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/webhook")
public class WebhookController {
    private final WebhookService webhookService;

    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping
    public ResponseEntity<String> receiveWebhook(
            @RequestBody Map<String, Object> body,
            @RequestParam Map<String, String> queryParams
            ) {
        System.out.println("Webhook recibido: " + body);
        System.out.println("Query: " + queryParams);

        String type = (String) body.get("type");

        if("payment".equals(type)) {
            Map<String, Object> data = (Map<String, Object>) body.get("data");
            Long paymentId = Long.valueOf(data.get("id").toString());

            webhookService.processPayment(paymentId);
        }
        return ResponseEntity.ok("Ok");
    }
}
