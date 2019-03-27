package com.example.demo.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.DemoApplication;
import com.example.demo.dao.User;

@Component
@RabbitListener(queues = DemoApplication.userQueueName)
public class HelloReciver2 {

	private static Logger logger = Logger.getLogger(HelloReciver2.class);
	
	@RabbitHandler
	public void process(User user) {
		logger.info("helloReciver2  -- Recive "+DemoApplication.userQueueName + ":" + user.toString());
	}
}
