package com.example.demo.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.DemoApplication;

@Component
@RabbitListener(queues = DemoApplication.queueName)
public class HelloReciver1 {
	
	private static Logger logger = Logger.getLogger(HelloReciver1.class);
	
	@RabbitHandler
    public void process(String hello) {
		logger.info("Receiver1  : " + hello);
    }

}
