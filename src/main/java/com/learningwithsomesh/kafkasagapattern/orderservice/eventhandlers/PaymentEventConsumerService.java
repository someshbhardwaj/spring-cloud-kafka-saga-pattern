package com.learningwithsomesh.kafkasagapattern.orderservice.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningwithsomesh.kafkasagapattern.model.enums.OrderStatus;
import com.learningwithsomesh.kafkasagapattern.model.enums.PaymentStatus;
import com.learningwithsomesh.kafkasagapattern.model.enums.ShippingOrderStatus;
import com.learningwithsomesh.kafkasagapattern.model.enums.ShippingStatus;
import com.learningwithsomesh.kafkasagapattern.model.evt.PaymentEvent;
import com.learningwithsomesh.kafkasagapattern.model.evt.ShippingEvent;
import com.learningwithsomesh.kafkasagapattern.orderservice.entity.PurchaseOrder;
import com.learningwithsomesh.kafkasagapattern.orderservice.repository.PurchaseOrderRepository;

import javax.transaction.Transactional;

@Service
public class PaymentEventConsumerService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    
    @Autowired
    private ShippingEventPublisherService seps;

    @Transactional
    public void consumePaymentEvent(PaymentEvent paymentEvent){
        this.purchaseOrderRepository.findById(paymentEvent.getOrderId())
                    .ifPresent(purchaseOrder -> {
                        purchaseOrder.setStatus(paymentEvent.getStatus().equals(PaymentStatus.APPROVED) ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED);
                        this.purchaseOrderRepository.save(purchaseOrder);
                        System.out.println("Order Event Completed !!!");
                        System.out.println("Raise Shipping Event !!!");
                        seps.raiseShippingCreatedEvent(purchaseOrder);
                    });
        
    }

}