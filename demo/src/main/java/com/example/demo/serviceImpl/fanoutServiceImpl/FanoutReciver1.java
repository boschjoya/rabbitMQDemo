package com.example.demo.serviceImpl.fanoutServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.A")
public class FanoutReciver1 {
	
	private static Logger logger = Logger.getLogger(FanoutReciver1.class);
	
	@RabbitHandler
    public void process(String msg) {
		logger.info("FanoutReceiverA  : " + msg);
    }

}
