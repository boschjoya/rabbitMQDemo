package com.example.demo.serviceImpl.toMany;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.DemoApplication;
import com.example.demo.serviceImpl.HelloReciver1;

/** 
* @author Joya 
* @version Mar 27, 2019 2:54:33 PM 
* @describe  
*/
@Component
@RabbitListener(queues = DemoApplication.queueNameSend)
public class reciver1 {
private static Logger logger = Logger.getLogger(reciver1.class);
	
	@RabbitHandler
    public void process(String hello) {
		logger.info("reciver1  : " + hello);
    }

}
