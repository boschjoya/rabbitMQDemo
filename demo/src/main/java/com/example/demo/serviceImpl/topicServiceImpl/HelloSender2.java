package com.example.demo.serviceImpl.topicServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component	
public class HelloSender2 {
	@Autowired
    private AmqpTemplate rabbitTemplate;
	
	private static Logger logger = Logger.getLogger(HelloSender2.class);
 
    public void send(String word1,String word2) {
        String msg1 = "I am topic.mesaage msg======";
        logger.info("sender1 : " + msg1 + word1);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", msg1 + word1);
        
        String msg2 = "I am topic.mesaagesssss msg########";
        logger.info("sender2 : " + msg2 + word2);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messagessss", msg2 + word2);
    }
}
