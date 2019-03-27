package com.example.demo.serviceImpl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.DemoApplication;


@Component
public class HelloSender1 {
	@Autowired
    private AmqpTemplate rabbitTemplate;
	private static Logger logger = Logger.getLogger(HelloSender1.class);
 
    public void send(String word) {
        String sendMsg = "hello1 " + word + new Date();
        logger.info("Sender1 : " + sendMsg);
        try {
        	this.rabbitTemplate.convertAndSend(DemoApplication.queueName, sendMsg);
        }catch(Exception e) {
        	logger.error("连接rabbitMq 服务器有问题", e);
        }
    }

}
