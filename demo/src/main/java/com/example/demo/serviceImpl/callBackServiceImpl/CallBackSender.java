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
public class CallBackSender implements  RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback{
	@Autowired
    private RabbitTemplate rabbitTemplatenew;
	
    public void send(String s) {
    	
//    	//开启rabbitMQ的事务
//    	rabbitTemplatenew.setChannelTransacted(true);
        
    	//RabbitMQ回应
    	rabbitTemplatenew.setConfirmCallback(this);
        
        //X消息投递不成功，则回调returnedMessage
    	rabbitTemplatenew.setReturnCallback(this);
    	
        String msg="callbackSender : i am callback sender" + s;
        System.out.println(msg );
        CorrelationData correlationData1 = new CorrelationData(UUID.randomUUID().toString());  
        System.out.println("callbackSender UUID: " + correlationData1.getId());  
        this.rabbitTemplatenew.convertAndSend("exchange", "topic.messagessss", msg, correlationData1);  
    }
 
    // ConfirmCallback实现
    // ACK=true仅仅标示消息已被Broker接收到，并不表示已成功投放至消息队列中
    // ACK=false标示消息由于Broker处理错误，消息并未处理成功
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // TODO Auto-generated method stub
        if (!ack) {
        	System.out.println("CallBackSender 消息发送失败" + cause + correlationData.toString());
            throw new RuntimeException("send error " + cause);
        }else {
        	System.out.println("CallBackSender 消息发送成功 " + correlationData.toString());
            //TODO 保存消息到数据库，发送者重发
        }
    }
    
    // x实现ReturnCallback
    // x当消息发送出去找不到对应路由队列时，将会把消息退回
    // x如果有任何一个路由队列接收投递消息成功，则不会退回消息
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		// TODO Auto-generated method stub
		System.out.println("sender return false" + message.toString()+"==="+replyText+"==="+exchange+"==="+routingKey);
	}
}
