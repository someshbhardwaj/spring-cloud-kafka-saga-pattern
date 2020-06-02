package com.learningwithsomesh.kafkasagapattern.model.evt;

import lombok.Data;

@Data
public class OrderEvent {
    private Integer orderId;
    private Integer userId;
    private Integer price;
}