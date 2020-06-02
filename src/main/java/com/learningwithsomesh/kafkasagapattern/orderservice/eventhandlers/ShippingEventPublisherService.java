package com.learningwithsomesh.kafkasagapattern.orderservice.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningwithsomesh.kafkasagapattern.model.evt.ShippingEvent;
import com.learningwithsomesh.kafkasagapattern.model.evt.ShppingAfterPaymentEvent;
import com.learningwithsomesh.kafkasagapattern.orderservice.entity.PurchaseOrder;

import reactor.core.publisher.FluxSink;

@Service
public class ShippingEventPublisherService {

    @Autowired FluxSink<ShppingAfterPaymentEvent> shippingEventChannel;

    public void raiseShippingCreatedEvent(final PurchaseOrder purchaseOrder) {
        ShppingAfterPaymentEvent sape = new ShppingAfterPaymentEvent();
        sape.setOrderId(purchaseOrder.getId());
        sape.setPincode(purchaseOrder.getPincode());
        sape.setUserId(purchaseOrder.getUserId());
        this.shippingEventChannel.next(sape);
    }

}