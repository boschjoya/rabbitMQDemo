package com.example.demo.serviceImpl.topicServiceImpl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
@RabbitListener(queues = "topic.message")
public class TopicReciver1 {
	
	private static Logger logger = Logger.getLogger(TopicReciver1.class);
	
	@RabbitHandler
    public void process(String msg,Channel channel, Message message) throws IOException{
		try {
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			logger.info("topicMessageReceiver1  : " +msg);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("receiver fail");
		}
		
    }

}
