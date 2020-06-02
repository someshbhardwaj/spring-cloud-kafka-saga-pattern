package com.learningwithsomesh.kafkasagapattern.model.dto;

import com.learningwithsomesh.kafkasagapattern.model.enums.OrderStatus;
import com.learningwithsomesh.kafkasagapattern.model.enums.ShippingOrderStatus;

import lombok.Data;

@Data
public class OrderResponseDTO {

    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private OrderStatus status;
    private Integer pincode;
    private ShippingOrderStatus shippingstatus;
    
}