package com.learningwithsomesh.kafkasagapattern.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.learningwithsomesh.kafkasagapattern.model.dto.OrderRequestDTO;
import com.learningwithsomesh.kafkasagapattern.model.dto.OrderResponseDTO;
import com.learningwithsomesh.kafkasagapattern.orderservice.entity.PurchaseOrder;
import com.learningwithsomesh.kafkasagapattern.orderservice.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDTO dto){
        return this.orderService.createOrder(dto);
    }

    @GetMapping("/all")
    public List<OrderResponseDTO> getOrders(){
        return this.orderService.getAll();
    }

}