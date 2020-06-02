package com.learningwithsomesh.kafkasagapattern.shippingservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learningwithsomesh.kafkasagapattern.model.evt.OrderEvent;
import com.learningwithsomesh.kafkasagapattern.model.evt.PaymentEvent;
import com.learningwithsomesh.kafkasagapattern.model.evt.ShippingEvent;
import com.learningwithsomesh.kafkasagapattern.model.evt.ShppingAfterPaymentEvent;
import com.learningwithsomesh.kafkasagapattern.paymentservice.eventhandlers.OrderEventProcessorService;
import com.learningwithsomesh.kafkasagapattern.shippingservice.eventhandlers.ShippingEventProcessorService;

import java.util.function.Function;

@Configuration
public class ShippingServiceConfig {

    @Autowired
    private ShippingEventProcessorService shippingEventProcessorService;

    @Bean
    public Function<ShppingAfterPaymentEvent, ShippingEvent> shippingEventProcessor(){
        return shippingEventProcessorService::processShippedEvent;
    }

}