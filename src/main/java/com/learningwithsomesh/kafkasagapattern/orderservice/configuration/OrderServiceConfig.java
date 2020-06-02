package com.learningwithsomesh.kafkasagapattern.orderservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learningwithsomesh.kafkasagapattern.model.evt.OrderEvent;
import com.learningwithsomesh.kafkasagapattern.model.evt.PaymentEvent;
import com.learningwithsomesh.kafkasagapattern.model.evt.ShippingEvent;
import com.learningwithsomesh.kafkasagapattern.model.evt.ShppingAfterPaymentEvent;
import com.learningwithsomesh.kafkasagapattern.orderservice.eventhandlers.PaymentEventConsumerService;
import com.learningwithsomesh.kafkasagapattern.orderservice.eventhandlers.ShippingEventConsumerService;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class OrderServiceConfig {

    @Autowired
    private PaymentEventConsumerService consumerService;
    
    @Autowired
    private ShippingEventConsumerService shippingConsumerService;

    @Bean
    public DirectProcessor<OrderEvent> getFlux(){
        return DirectProcessor.create();
    }

    @Bean
    public DirectProcessor<ShppingAfterPaymentEvent> getFlux1(){
        return DirectProcessor.create();
    }

    
    @Bean
    public FluxSink<OrderEvent> orderEventChannel(DirectProcessor<OrderEvent> processor){
        return processor.sink();
    }
    
    @Bean
    public FluxSink<ShppingAfterPaymentEvent> paymentToShipingEventChannel(DirectProcessor<ShppingAfterPaymentEvent> processor){
        return processor.sink();
    }

    @Bean
    public Supplier<Flux<OrderEvent>> orderEventPublisher(DirectProcessor<OrderEvent> processor){
        return () -> processor;
    }
    
    @Bean
    public Supplier<Flux<ShppingAfterPaymentEvent>> shippingEventPublisher(DirectProcessor<ShppingAfterPaymentEvent> processor){
        return () -> processor;
    }

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){
        return consumerService::consumePaymentEvent;
    }
    

    @Bean
    public Consumer<ShippingEvent> shippingEventConsumer(){
    return shippingConsumerService::consumeShippingEvent;
}

}