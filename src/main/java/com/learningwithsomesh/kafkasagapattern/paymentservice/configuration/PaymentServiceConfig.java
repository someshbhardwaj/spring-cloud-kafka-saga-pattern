package com.learningwithsomesh.kafkasagapattern.paymentservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learningwithsomesh.kafkasagapattern.model.evt.OrderEvent;
import com.learningwithsomesh.kafkasagapattern.model.evt.PaymentEvent;
import com.learningwithsomesh.kafkasagapattern.paymentservice.eventhandlers.OrderEventProcessorService;

import java.util.function.Function;

@Configuration
public class PaymentServiceConfig {

    @Autowired
    private OrderEventProcessorService orderEventProcessorService;

    @Bean
    public Function<OrderEvent, PaymentEvent> orderEventProcessor(){
        return orderEventProcessorService::processOrderEvent;
    }

}