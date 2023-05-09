package com.mscv.orderservice.controller;

import com.mscv.orderservice.dto.OrderRequest;
import com.mscv.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String order(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "Pedido realizado correctamente";
    }
}
