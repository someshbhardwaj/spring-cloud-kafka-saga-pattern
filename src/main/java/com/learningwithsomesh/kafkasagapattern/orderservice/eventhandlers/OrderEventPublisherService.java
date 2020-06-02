package com.learningwithsomesh.kafkasagapattern.orderservice.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningwithsomesh.kafkasagapattern.model.evt.OrderEvent;
import com.learningwithsomesh.kafkasagapattern.orderservice.entity.PurchaseOrder;

import reactor.core.publisher.FluxSink;

@Service
public class OrderEventPublisherService {

    @Autowired
    private FluxSink<OrderEvent> orderEventChannel;

    
    public void raiseOrderCreatedEvent(final PurchaseOrder purchaseOrder){
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setUserId(purchaseOrder.getUserId());
        orderEvent.setPrice(purchaseOrder.getPrice());
        orderEvent.setOrderId(purchaseOrder.getId());
        //orderEvent.setPincode(purchaseOrder.getPincode());
        this.orderEventChannel.next(orderEvent);
    }

}