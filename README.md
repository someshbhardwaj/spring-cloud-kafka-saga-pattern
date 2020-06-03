# spring-cloud-kafka-saga-pattern

Pre-requisites:
Install Kafka
Install Spring
Install Java

Start Zookeper 
--------------

zookeeper-server-start.bat d:\Software\kafka_2.13-2.5.0\config\zookeeper.properties


Start Kafka server
------------------

kafka-server-start.bat d:\Software\kafka_2.13-2.5.0\config\server.properties



Create Order Request Image
![Image of Create An Order](https://github.com/someshbhardwaj/spring-cloud-kafka-saga-pattern/blob/master/images/CreateOrder.png)

Get All Order Request Image
![Image of Search](https://github.com/someshbhardwaj/spring-cloud-kafka-saga-pattern/blob/master/images/GetAll_Orders.png)

 Project Code Flow Image
![Image of Project Code Flow](https://github.com/someshbhardwaj/spring-cloud-kafka-saga-pattern/blob/master/images/Code%20Flow.jpg)


```yml
Snippets from Application.yml 
spring.cloud.stream:
  function:
     definition: orderEventPublisher;shippingEventPublisher;orderEventProcessor;shippingEventProcessor;paymentEventConsumer;shippingEventConsumer
  bindings:
    orderEventPublisher-out-0:
      destination: order-events
    orderEventProcessor-in-0:
      destination: order-events
    orderEventProcessor-out-0:
      destination: payment-events
    paymentEventConsumer-in-0:
      destination: payment-events
    shippingEventPublisher-out-0:
      destination: shppingafterpayment-events  
    shippingEventProcessor-in-0:
      destination: shppingafterpayment-events
    shippingEventProcessor-out-0:
      destination: shipping-events
    shippingEventConsumer-in-0:
      destination: shipping-events
```

You can further take the code and make the changes to add the DB to it. 
You can also make the kafka cluster and use it and also add the concurrency to the same code.
