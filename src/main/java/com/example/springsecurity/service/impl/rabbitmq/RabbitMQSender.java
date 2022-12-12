package com.example.springsecurity.service.impl.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${username.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${username.rabbitmq.routingkey}")
	private String routingkey;	

	public void send(String username) {
		amqpTemplate.convertAndSend(exchange, routingkey, username);
		log.info("Send username to queue = " + username);
	    
	}
}