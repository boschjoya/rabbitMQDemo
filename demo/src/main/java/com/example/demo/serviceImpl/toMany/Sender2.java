package com.example.demo.serviceImpl.toMany;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.DemoApplication;

/** 
* @author Joya 
* @version Mar 27, 2019 2:59:50 PM 
* @describe  
*/
@Component
public class Sender2 {
	@Autowired
    private AmqpTemplate rabbitTemplate;
	private static Logger logger = Logger.getLogger(Sender2.class);
 
    public void send(String word) {
        String sendMsg = word + "  "+ new Date();
        logger.info("Sender2 : " + sendMsg);
        try {
        	this.rabbitTemplate.convertAndSend(DemoApplication.queueNameSend, sendMsg);
        }catch(Exception e) {
        	logger.error("连接rabbitMq 服务器有问题", e);
        }
    }

}
