package com.example.demo.serviceImpl.topicServiceImpl;

import java.io.IOException;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.messagessss")
public class TopicReciver2 {
	
	private static Logger logger = Logger.getLogger(TopicReciver2.class); 

	@RabbitHandler
    public void process(String msg,Channel channel, Message message)throws IOException {
        //logger.info("topicMessageReceiver2  : " +msg);
        try {
            //x告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
        	channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            logger.info("receiver success --- topicMessagessssReceiver2 " + msg);
            //x手动回复false ack
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            //x拒绝消息
            //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            e.printStackTrace();
            //x丢弃这条消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            logger.error("receiver fail");
        }
    }
}
