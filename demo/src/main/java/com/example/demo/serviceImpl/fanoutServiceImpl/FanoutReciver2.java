package com.example.demo.serviceImpl.fanoutServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.B")
public class FanoutReciver2 {
	
	private static Logger logger = Logger.getLogger(FanoutReciver2.class);

	@RabbitHandler
    public void process(String msg) {
        logger.info("FanoutReceiverB  : " + msg);
    }
}
