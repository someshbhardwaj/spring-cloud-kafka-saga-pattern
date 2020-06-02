package com.learningwithsomesh.kafkasagapattern.orderservice.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.learningwithsomesh.kafkasagapattern.model.enums.OrderStatus;
import com.learningwithsomesh.kafkasagapattern.model.enums.ShippingOrderStatus;
import com.learningwithsomesh.kafkasagapattern.model.enums.ShippingStatus;

@Data
@Entity
@ToString
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private OrderStatus status;
    private Integer pincode;
    private ShippingOrderStatus shippingstatus;

}