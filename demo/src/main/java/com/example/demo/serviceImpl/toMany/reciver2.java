package com.example.demo.serviceImpl.toMany;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.DemoApplication;

/** 
* @author Joya 
* @version Mar 27, 2019 2:56:20 PM 
* @describe  
*/
@Component
@RabbitListener(queues = DemoApplication.queueNameSend)
public class reciver2 {
private static Logger logger = Logger.getLogger(reciver2.class);
	
	@RabbitHandler
    public void process(String hello) {
		logger.info("reciver2  : " + hello);
    }

}
