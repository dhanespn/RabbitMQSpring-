package com.example.demo;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Runner {

	@Autowired
    private RabbitTemplate rabbitTemplate;
    
	@Async
    public void triggerAsynch() {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RabbitmqApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ1234567890!");
    }

}