package com.example.demo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	@GetMapping("/hai")
	public String triggerRabbit() throws Exception {
		Thread.sleep(10000);
		rabbitTemplate.convertAndSend(RabbitmqApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ1234567890!");
		rabbitTemplate.convertAndSend(RabbitmqApplication.topicExchangeName, "foo.bar.baz", "Hello from asdasdasdasdasdsa!");
		//Thread.sleep(50000);
		return "Triggered";
	}
	
	@GetMapping("/checktheStatusAMQP")
	//@RabbitListener(queues=RabbitmqApplication.queueName)
    public String receivedMessage(String msg) {
		return new String(rabbitTemplate.receive(RabbitmqApplication.queueName).getBody());
    }
}
