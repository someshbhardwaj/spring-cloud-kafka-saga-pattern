package com.learningwithsomesh.kafkasagapattern.model.evt;

import com.learningwithsomesh.kafkasagapattern.model.enums.ShippingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShippingEvent {

    private Integer orderId;
    private Integer pincode;

    private ShippingStatus status;

    public ShippingEvent(Integer orderId) {
        this.orderId = orderId;
    }
    
 }