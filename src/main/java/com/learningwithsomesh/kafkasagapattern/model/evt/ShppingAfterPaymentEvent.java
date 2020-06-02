package com.learningwithsomesh.kafkasagapattern.model.evt;

import lombok.Data;

@Data
public class ShppingAfterPaymentEvent {
    private Integer orderId;
    private Integer userId;
    private Integer pincode;
}