package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.OrderDetail;
import com.example.goblidas_backend.services.OrderDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderdetail")
public class OrderDetailController extends BaseController<OrderDetail> {

    public OrderDetailController(OrderDetailService orderDetailService){
        super(orderDetailService);
    }
}
