package com.learningwithsomesh.kafkasagapattern.orderservice.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningwithsomesh.kafkasagapattern.model.enums.OrderStatus;
import com.learningwithsomesh.kafkasagapattern.model.enums.PaymentStatus;
import com.learningwithsomesh.kafkasagapattern.model.enums.ShippingOrderStatus;
import com.learningwithsomesh.kafkasagapattern.model.enums.ShippingStatus;
import com.learningwithsomesh.kafkasagapattern.model.evt.PaymentEvent;
import com.learningwithsomesh.kafkasagapattern.model.evt.ShippingEvent;
import com.learningwithsomesh.kafkasagapattern.orderservice.repository.PurchaseOrderRepository;

import javax.transaction.Transactional;

@Service
public class ShippingEventConsumerService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    
    @Transactional
    public void consumeShippingEvent(ShippingEvent shippingEvent){
        System.out.println("********* Code inside consumeShippingEvent with ShippingEvent"+ shippingEvent);
        System.out.println("********* Code inside consumeShippingEvent with purchaseOrder"+ purchaseOrderRepository.findById(shippingEvent.getOrderId()));

        this.purchaseOrderRepository.findById(shippingEvent.getOrderId())
                    .ifPresent(purchaseOrder -> {
                    	if(purchaseOrder.getStatus().equals(OrderStatus.ORDER_COMPLETED)){
	                        purchaseOrder.setShippingstatus(shippingEvent.getStatus().equals(ShippingStatus.NOT_SUPPORTED) ? ShippingOrderStatus.ORDER_NOT_SHIPPABLE: ShippingOrderStatus.ORDER_SHIPPABLE);
	                        System.out.println("Final Print    "+purchaseOrder);
	                        this.purchaseOrderRepository.save(purchaseOrder);
                    	}else {
	                        purchaseOrder.setShippingstatus(ShippingOrderStatus.ORDER_NOT_COMPLETE_THUS_NOT_SHIPPABLE);

                    	}
                    });
    }

}