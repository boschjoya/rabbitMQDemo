package com.example.demo.serviceImpl.callBackServiceImpl;

import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** 
* @author Joya 
* @version Mar 15, 2019 9:18:46 AM 
* @describe  
*/

@Component
public class CallBackSender implements  RabbitTemplate.ReturnCallback{
	@Autowired
    private RabbitTemplate rabbitTemplatenew;
	
//	@Transactional
    public void send(String s) {
    	
//    	//开启rabbitMQ的事务
//    	rabbitTemplatenew.setChannelTransacted(true);
        
    	//消息到exchange是否成功
        rabbitTemplatenew.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("CallBackSender 消息发送失败" + cause + correlationData.toString());
            } else {
                System.out.println("CallBackSender 消息发送成功 " + correlationData.toString());
                //TODO 保存消息到数据库，发送者重发
            }
        });
        
        //消息从exchange到queue不成功，则回调returnedMessage
    	rabbitTemplatenew.setReturnCallback(this);
    	
        String msg="callbackSender : i am callback sender" + s;
        System.out.println(msg );
        CorrelationData correlationData1 = new CorrelationData(UUID.randomUUID().toString());  
        System.out.println("callbackSender UUID: " + correlationData1.getId());  
        this.rabbitTemplatenew.convertAndSend("exchange", "topic.messages", msg, correlationData1);  
    }
 
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        // TODO Auto-generated method stub
//        if (!ack) {
//        	System.out.println("send message failed: " + cause); //+ correlationData.toString());
//            throw new RuntimeException("send error " + cause);
//        }else {
//        	System.out.println("callbakck confirm: " + correlationData.getId() + "####" + cause + "####" + ack);
//        }
//    }
    
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		// TODO Auto-generated method stub
		System.out.println("sender return false" + message.toString()+"==="+replyText+"==="+exchange+"==="+routingKey);
	}
}
