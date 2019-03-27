package com.example.demo.serviceImpl.topicServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.messages")
public class TopReciver2 {
	
	private static Logger logger = Logger.getLogger(TopReciver2.class); 

	@RabbitHandler
    public void process(String msg) {
        logger.info("topicMessageReceiver2  : " +msg);
    }
}
