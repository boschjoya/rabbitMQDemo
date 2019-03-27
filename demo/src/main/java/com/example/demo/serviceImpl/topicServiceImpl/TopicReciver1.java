package com.example.demo.serviceImpl.topicServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.message")
public class TopicReciver1 {
	
	private static Logger logger = Logger.getLogger(TopicReciver1.class);
	
	@RabbitHandler
    public void process(String msg) {
		logger.info("topicMessageReceiver1  : " +msg);
    }

}
