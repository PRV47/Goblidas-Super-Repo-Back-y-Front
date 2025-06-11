package com.example.goblidas_backend.services;

import com.example.goblidas_backend.entities.Order;
import com.example.goblidas_backend.entities.enums.OrderStatus;
import com.example.goblidas_backend.repositories.OrderRepository;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WebhookService {

    private final OrderRepository orderRepository;

    public WebhookService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public void processPayment(Long paymentId) {
        try {
            PaymentClient paymentClient = new PaymentClient();
            Payment payment = paymentClient.get(paymentId);

            String status = payment.getStatus();
            String externalReference = payment.getExternalReference();
            BigDecimal amount = payment.getTransactionAmount();
            String payerEmail = payment.getPayer().getEmail();

            System.out.printf("Pago recibido - Estado: %s, Email: %s, Orden: %s%n", status, payerEmail, externalReference);

            if(externalReference != null) {
                Long orderId = Long.valueOf(externalReference);
                Order order = orderRepository.findById(orderId)
                        .orElseThrow(() -> new RuntimeException("Orden no encontrada para el paymentId"));

                switch (status) {
                    case "approved" -> order.setOrderStatus(OrderStatus.PAID);
                    case "rejected" -> order.setOrderStatus(OrderStatus.REJECTED);
                    case "in_process" -> order.setOrderStatus(OrderStatus.PROCESSING);
                    default -> order.setOrderStatus(OrderStatus.PENDING);
                }

                orderRepository.save(order);
            }
        } catch (MPException e) {
            e.printStackTrace();
        } catch (MPApiException e) {
            throw new RuntimeException(e);
        }
    }

}
