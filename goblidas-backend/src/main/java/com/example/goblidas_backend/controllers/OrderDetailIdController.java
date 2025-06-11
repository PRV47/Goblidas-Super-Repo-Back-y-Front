package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.OrderDetailId;
import com.example.goblidas_backend.services.OrderDetailIdService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderdetailid")
public class OrderDetailIdController extends BaseController<OrderDetailId> {

    public OrderDetailIdController(OrderDetailIdService orderDetailIdService){
        super(orderDetailIdService);
    }
}
