package com.learningwithsomesh.kafkasagapattern.paymentservice.eventhandlers;

import org.springframework.stereotype.Service;

import com.learningwithsomesh.kafkasagapattern.model.enums.PaymentStatus;
import com.learningwithsomesh.kafkasagapattern.model.evt.OrderEvent;
import com.learningwithsomesh.kafkasagapattern.model.evt.PaymentEvent;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderEventProcessorService {

    // user - credit limit
    public static final Map<Integer, Integer> userMap = new HashMap<>();

    static {
        userMap.put(1, 1000);
        userMap.put(2, 2000);
        userMap.put(3, 1000);
        userMap.put(4, 1000);
        userMap.put(5, 1000);
    }

    public PaymentEvent processOrderEvent(OrderEvent orderEvent){
        var price = orderEvent.getPrice();
        var creditLimit = userMap.get(orderEvent.getUserId());
        PaymentEvent paymentEvent = new PaymentEvent(orderEvent.getOrderId());
        if(creditLimit >= price){
            paymentEvent.setStatus(PaymentStatus.APPROVED);
            userMap.computeIfPresent(orderEvent.getUserId(), (k, v) -> v - price);
        }else{
            paymentEvent.setStatus(PaymentStatus.REJECTED);
        }
        System.out.println("XXXXXXXXX Code going out of processOrderEvent with paymentEvent"+ paymentEvent);
        System.out.println("XXXXXXXXX Code going out of processOrderEvent with orderEvent"+ orderEvent);

        return paymentEvent;
    }

}