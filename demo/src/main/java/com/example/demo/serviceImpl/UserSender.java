package com.example.demo.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.DemoApplication;
import com.example.demo.dao.User;

@Component
public class UserSender {
	@Autowired
	private AmqpTemplate rabbitmqTemplate;
	private Logger logger = Logger.getLogger(UserSender.class);
	
	public void sender(User user) {
		if(user != null) {
			logger.info("UserSender : " + user.toString());
	        try {
	        	this.rabbitmqTemplate.convertAndSend(DemoApplication.userQueueName, user);
	        }catch(Exception e) {
	        	logger.error("连接rabbitMq 服务器有问题", e);
	        }
		}	
	}
	
	

}
